package Databases.DatabaseFactory;

import Databases.PropertiesHelper;

import java.sql.*;

public class DatabaseFactoryHelper {

    public static Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    PropertiesHelper.getInstance().getDbUrl(),
                    PropertiesHelper.getInstance().getDbUser(),
                    PropertiesHelper.getInstance().getDbPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected");
        return conn;
    }
}