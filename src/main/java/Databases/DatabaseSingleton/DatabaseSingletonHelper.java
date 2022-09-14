package Databases.DatabaseSingleton;

import Databases.PropertiesHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A DatabaseSingletonHelper class will produce single DB connection,
 * and it will make sure that only single connection gets created.
 */
public class DatabaseSingletonHelper {

    static Connection conn;
    private static DatabaseSingletonHelper instance = new DatabaseSingletonHelper();

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