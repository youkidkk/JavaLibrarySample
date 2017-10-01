package dbunit.sample;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("javadoc")
public class DbUnitSample {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Class.forName("org.h2.Driver");

        try (Connection conn = getConnection()) {
            executeSql(conn,
                    "drop table if exists test;");
            executeSql(conn,
                    "create table if not exists test (id identity, number int, text varchar);");
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/DbUnitSample", "sa", "");
    }

    @Before
    public void setUp() throws SQLException, DatabaseUnitException, IOException {
        try (Connection conn = getConnection()) {
            IDatabaseConnection connection = new DatabaseConnection(conn);
            IDataSet dataset = new XlsDataSet(Paths.get("./data/test_data.xlsx").toFile());
            DatabaseOperation.DELETE.execute(connection, dataset);
            DatabaseOperation.INSERT.execute(connection, dataset);
        }
    }

    @After
    public void tearDown() throws SQLException, DatabaseUnitException, IOException {
        try (Connection conn = getConnection()) {
            IDatabaseConnection connection = new DatabaseConnection(conn);
            IDataSet dataset = new XlsDataSet(Paths.get("./data/test_data.xlsx").toFile());
            DatabaseOperation.DELETE.execute(connection, dataset);
        }
    }

    @Test
    public void test() throws SQLException {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            executeSql(conn, "insert into test (number, text) values (?, ?);", 4, "text4");
            printResultList(executeQuerySql(conn, "select * from test;"));
        }
    }

    private static void executeSql(Connection conn, String sql, Object... args)
            throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setArguments(ps, args);
            ps.execute();
        }
    }

    private static List<Map<String, Object>> executeQuerySql(Connection conn, String sql,
            Object... args)
            throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setArguments(ps, args);
            ResultSet rs = ps.executeQuery();

            return getResultList(rs);
        }
    }

    private static List<Map<String, Object>> getResultList(ResultSet rs) throws SQLException {
        List<String> columnNames = getColumnNames(rs);

        List<Map<String, Object>> resultList = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (String columnName : columnNames) {
                map.put(columnName, rs.getObject(columnName));
            }
            resultList.add(map);
        }
        return resultList;
    }

    private static List<String> getColumnNames(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(rsmd.getColumnName(i));
        }
        return columnNames;
    }

    private static void printResultList(List<Map<String, Object>> resultList) {
        for (Map<String, Object> map : resultList) {
            List<String> result = new ArrayList<>();
            for (Entry<String, Object> entry : map.entrySet()) {
                result.add(entry.getKey() + " = " + entry.getValue());
            }
            System.out.println(String.join(", ", result));
        }
    }

    private static void setArguments(PreparedStatement ps, Object... args) throws SQLException {
        if (args != null && args.length > 0) {
            int index = 1;
            for (Object arg : args) {
                ps.setObject(index, arg);
                index++;
            }
        }
    }

}
