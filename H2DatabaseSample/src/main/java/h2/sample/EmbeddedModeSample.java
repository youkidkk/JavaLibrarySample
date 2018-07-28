package h2.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

@SuppressWarnings("javadoc")
public class EmbeddedModeSample {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedModeSample.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd hh:mm:ss.SSS");

    public void execute() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./db/test", "sa", "")) {
            executeSql(conn,
                    "create table if not exists test (id identity, number int, text varchar, datetime timestamp);");

            IntStream.range(1, 99).forEach(i -> {
                try {
                    executeSql(conn, "insert into test (number, text, datetime) values (?, ?, ?);",
                            i,
                            "text : " + i,
                            LocalDateTime.now().format(formatter));
                } catch (SQLException e) {
                    throw new RuntimeException("Insert 失敗", e);
                }
            });

            List<Map<String, Object>> resultList = executeQuerySql(conn,
                    "select * from test where id % ? = 0;", 10);
            printResultList(resultList);
        } catch (SQLException | RuntimeException e) {
            logger.error("例外発生 : ", e);
        }
    }

    private void executeSql(Connection conn, String sql, Object... args)
            throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setArguments(ps, args);
            ps.execute();
        }
    }

    private List<Map<String, Object>> executeQuerySql(Connection conn, String sql,
            Object... args)
            throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setArguments(ps, args);
            ResultSet rs = ps.executeQuery();

            return getResultList(rs);
        }
    }

    private List<Map<String, Object>> getResultList(ResultSet rs) throws SQLException {
        List<String> columnNames = getColumnNames(rs);

        List<Map<String, Object>> resultList = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (String columnName : columnNames) {
                Object obj = rs.getObject(columnName);
                map.put(columnName, obj);
            }
            resultList.add(map);
        }
        return resultList;
    }

    private List<String> getColumnNames(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(rsmd.getColumnName(i));
        }
        return columnNames;
    }

    private void printResultList(List<Map<String, Object>> resultList) {
        for (Map<String, Object> map : resultList) {
            List<String> result = new ArrayList<>();
            for (Entry<String, Object> entry : map.entrySet()) {
                result.add(entry.getKey() + " = " + entry.getValue());
            }
            logger.info(String.join(", ", result));
        }
    }

    private void setArguments(PreparedStatement ps, Object... args) throws SQLException {
        if (args != null && args.length > 0) {
            int index = 1;
            for (Object arg : args) {
                ps.setObject(index, arg);
                index++;
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new EmbeddedModeSample().execute();
    }

}
