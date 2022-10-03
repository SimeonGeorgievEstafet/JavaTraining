package Databases;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class will handle basic CRUD operations.
 */
public class DatabaseManager<T> {

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

    public static void executeUpdate(String query) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method delete() will delete record in the db by id and query.
     */
    public static void delete(int id, String query) {
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
    public static void getRecordsCount(String query) {
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
    public T getByID(String query, T object) {
        List<T> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<T>>) object);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(objectList.get(0));
            return objectList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T getByIdDbUtils(String QUERY, BeanHandler<T> beanHandler) {
        T object;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            object = queryRunner.query(conn, QUERY, beanHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public List<T> getByIdsDbUtils(String query, BeanListHandler<T> beanListHandler) {
        List<T> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            objectList = queryRunner.query(conn, query, beanListHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objectList;
    }

    /**
     * getByID() method will return Object(Customer,Order,CustomerAddress,Product) by,
     * required id, query and ObjectHandler(ProductHandler,OrderHandler...)
     */
    public List<T> getByIDs(String query, T object) {
        List<T> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<T>>) object);
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
