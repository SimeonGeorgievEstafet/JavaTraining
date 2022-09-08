package Databases.DatabaseSingleton;

import Databases.PropertiesHelper;

import java.sql.*;

/**
 * A DatabaseSingletonHelper class will produce single DB connection,
 * and it will make sure that only single connection gets created.
 */
public class DatabaseSingletonHelper {

    private static DatabaseSingletonHelper instance = new DatabaseSingletonHelper();
    static Connection conn;

    private DatabaseSingletonHelper() {
    }

    public static Connection getInstance() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(
                        PropertiesHelper.getInstance().getDbUrl(),
                        PropertiesHelper.getInstance().getDbUser(),
                        PropertiesHelper.getInstance().getDbPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}