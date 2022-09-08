package Databases.DatabaseFactory;

import java.sql.*;

public class TestFactoryMethod {

    // Testing the Factory pattern connections
    public static void main(String[] args) {

        String QUERY = "SELECT * FROM customers";

        try (Connection conn = DatabaseFactoryHelper.createConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            // Extract data from result set
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            while (rs.next()) {
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


