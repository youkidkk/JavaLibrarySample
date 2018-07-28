package h2.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void execute()
            throws ClassNotFoundException, SQLException, URISyntaxException, IOException {
        Class.forName("org.h2.Driver");

        createTable();

        try (Connection conn = getConnection();
                PreparedStatement insertStmt = //
                        conn.prepareStatement(getResourceText("insert.sql"));
                PreparedStatement selectStmt = //
                        conn.prepareStatement(getResourceText("select.sql"));) {
            IntStream.range(1, 99).forEach(i -> {
                try {
                    executeSql(conn, insertStmt,
                            i,
                            "text : " + i,
                            LocalDateTime.now().format(formatter));
                } catch (SQLException e) {
                    throw new RuntimeException("Insert 失敗", e);
                }
            });

            List<Map<String, Object>> resultList = executeQuerySql(conn, selectStmt, 10);
            printResultList(resultList);
        }
    }

    private void createTable() throws SQLException, URISyntaxException, IOException {
        try (Connection conn = getConnection()) {
            executeSql(conn, getResourceText("create_table.sql"));
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./db/test", "sa", "");
    }

    private String getResourceText(String resourceName) throws URISyntaxException, IOException {
        URL url = this.getClass().getClassLoader().getResource(resourceName);
        Path path = Paths.get(url.toURI());
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

    private void executeSql(Connection conn, String sql) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    private void executeSql(Connection conn, PreparedStatement ps, Object... args)
            throws SQLException {
        setArguments(ps, args);
        ps.execute();
    }

    private List<Map<String, Object>> executeQuerySql(Connection conn, PreparedStatement ps,
            Object... args) throws SQLException {
        setArguments(ps, args);
        ResultSet rs = ps.executeQuery();

        return getResultList(rs);
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
        try {
            new EmbeddedModeSample().execute();
        } catch (SQLException | URISyntaxException | IOException e) {
            logger.error("例外発生 : ", e);
        }
    }

}
