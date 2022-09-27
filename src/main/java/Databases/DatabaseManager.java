package Databases;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.*;
import java.util.List;

/**
 * This class will handle basic CRUD operations.
 */
public class DatabaseManager {

    public static ResultSet executeQuery(String query) {
        ResultSet rs;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    private static void ResultSetPrint(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }

    public static void executeUpdate(String query) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet ExecuteQueryAndReturnInt(String query) {
        ResultSet rs;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    /**
     * Method delete() will delete record in the db by id and query.
     */
    public void delete(int id, String query) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeQuery();
            System.out.println(id + " was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method delete() will delete record in the db by id and query.
     */
    public void getRecordsCount(String query) {
        String count;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, query);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getString(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getByID() method will return Object(Customer,Order,CustomerAddress,Product) by,
     * required id, query and ObjectHandler(ProductHandler,OrderHandler...)
     */
    public Object getByID(String query, Object object) {
        List<Object> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<Object>>) object);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(objectList.get(0));
            return objectList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getByID() method will return Object(Customer,Order,CustomerAddress,Product) by,
     * required id, query and ObjectHandler(ProductHandler,OrderHandler...)
     */
    public List<Object> getByIDs(String query, Object object) {
        List<Object> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<Object>>) object);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(objectList);
            //Return List not Object
            return objectList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
