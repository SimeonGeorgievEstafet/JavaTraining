package Databases;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Helpers.SQLQueries;
import POJO.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.*;
import java.util.List;

/**
 * This class will handle basic CRUD operations.
 */
public class DatabaseManager {


    public static void executeQuery(String query) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(query);
            if (query.contains("TRUNCATE"))
                ps.executeUpdate();
            else
                ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getByID() method will return Object(Customer,Order,CustomerAddress,Product) by,
     * required id, query and ObjectHandler(ProductHandler,OrderHandler...)
     */
    public Object getByID(int id, String query, Object object) {
        List<Object> objectList;
        QueryRunner queryRunner = new QueryRunner();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<Object>>) object, id);
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
     * Method save() will receive object(Customer,Product ...) and will execute query.
     */
    public void save(Object object, String query) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            ResultSet rs = null;
            ResultSetMetaData rsmd = null;
            PreparedStatement ps;
            switch (query) {
                case SQLQueries.SAVE_PRODUCT:
                    Product product = (Product) object;
                    ps = conn.prepareStatement(query);
                    ps.setString(1, product.getProductName());
                    ps.setInt(2, product.getAvailableQuantity());
                    ps.setString(3, product.getProductType());
                    ps.setDouble(4, product.getPriceWithoutVat());
                    ps.setDouble(5, product.getPriceWithVat());
                    ps.setBoolean(6, product.getInStock());
                    ps.setInt(7, product.getSupplier());
                    rs = ps.executeQuery();
                    break;
                case SQLQueries.SAVE_ORDER:
                    Order order = (Order) object;
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, order.getCustomerId());
                    ps.setBoolean(2, order.getIsOrderCompleted());
                    ps.setBoolean(3, order.getIsOrderPaid());
                    System.out.println(ps);
                    rs = ps.executeQuery();
                    break;
                case SQLQueries.SAVE_CUSTOMER_ADDRESS:
                    CustomerAddress customerAddress = (CustomerAddress) object;
                    ps = conn.prepareStatement(query);
                    ps.setString(1, customerAddress.getAddress());
                    ps.setString(2, customerAddress.getCity());
                    ps.setString(3, customerAddress.getProvince());
                    ps.setString(4, customerAddress.getState());
                    ps.setInt(5, customerAddress.getPostalCode());
                    ps.setString(6, customerAddress.getCountry());
                    System.out.println(ps);
                    rs = ps.executeQuery();
                    break;
                case SQLQueries.SAVE_PRODUCT_ORDER:
                    ProductOrder productOrder = (ProductOrder) object;
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, Integer.parseInt(productOrder.getOrderId()));
                    ps.setInt(2, Integer.parseInt(productOrder.getProductId()));
                    ps.setInt(3, productOrder.getOrderedQuantity());
                    System.out.println(ps);
                    rs = ps.executeQuery();
                    break;
                case SQLQueries.SAVE_CUSTOMER:
                    Customer customer = (Customer) object;
                    ps = conn.prepareStatement(query);
                    ps.setString(1, customer.getName());
                    ps.setString(2, customer.getEmail());
                    ps.setString(3, customer.getPhone());
                    ps.setInt(4, customer.getAge());
                    ps.setBoolean(5, customer.isGdpr());
                    ps.setBoolean(6, customer.isCustomerProfileStatus());
                    ps.setString(7, customer.getReason());
                    ps.setString(8, customer.getNotes());
                    System.out.println(ps);
                    rs = ps.executeQuery();
                    break;
            }
            // Extract data from result set
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
            throw new RuntimeException(e);
        }
    }

//    /**
//     * getByID() method will return Object(Customer,Order,CustomerAddress,Product) by,
//     * required id, query and ObjectHandler(ProductHandler,OrderHandler...)
//     */
//    public Object getByID(int id, String query, Object object) {
//        List<Object> objectList;
//        QueryRunner queryRunner = new QueryRunner();
//        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
//            try {
//                objectList = queryRunner.query(conn, query, (ResultSetHandler<? extends List<Object>>) object, id);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(objectList.get(0));
//            return objectList.get(0);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
     * Method update() requires Objects of type(Customer,Product,Order,CustomerAddress),
     * query for update and id of the object.
     */
    public void update(Object object, String query, int id) {
        PreparedStatement ps;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            switch (query) {
                case SQLQueries.UPDATE_PRODUCT:
                    Product product = (Product) object;
                    ps = conn.prepareStatement(SQLQueries.UPDATE_PRODUCT);
                    ps.setString(1, product.getProductName());
                    ps.setInt(2, product.getAvailableQuantity());
                    ps.setString(3, product.getProductType());
                    ps.setDouble(4, product.getPriceWithoutVat());
                    ps.setDouble(5, product.getPriceWithVat());
                    ps.setBoolean(6, product.getInStock());
                    ps.setInt(7, product.getSupplier());
                    ps.setInt(8, id);
                    ps.executeQuery();
                    System.out.println("Product is updated!");
                    break;
                case SQLQueries.UPDATE_ORDER:
                    ps = conn.prepareStatement(SQLQueries.UPDATE_ORDER);
                    ps.setInt(1, id);
                    ps.executeQuery();
                    System.out.println("Order is updated!");
                    break;
                case SQLQueries.UPDATE_CUSTOMER_ADDRESS:
                    CustomerAddress customerAddress = (CustomerAddress) object;
                    ps = conn.prepareStatement(SQLQueries.UPDATE_CUSTOMER_ADDRESS);
                    ps.setString(1, customerAddress.getAddress());
                    ps.setString(2, customerAddress.getCity());
                    ps.setString(3, customerAddress.getProvince());
                    ps.setString(4, customerAddress.getState());
                    ps.setInt(5, customerAddress.getPostalCode());
                    ps.setString(6, customerAddress.getCountry());
                    ps.setInt(7, id);
                    ps.executeQuery();
                    System.out.println("Customer address is updated!");
                    break;
                case SQLQueries.ACTIVATE_CUSTOMER:
                    ps = conn.prepareStatement(SQLQueries.ACTIVATE_CUSTOMER);
                    ps.setInt(1, id);
                    ps.executeQuery();
                    System.out.println("Customer is active!");
                    break;
                case SQLQueries.DEACTIVATE_CUSTOMER:
                    ps = conn.prepareStatement(SQLQueries.DEACTIVATE_CUSTOMER);
                    ps.setInt(1, id);
                    ps.executeQuery();
                    System.out.println("Customer is deactivated!");
                    break;
                case SQLQueries.UPDATE_PRODUCT_ORDER:
                    ProductOrder productOrder = (ProductOrder) object;
                    ps = conn.prepareStatement(SQLQueries.UPDATE_PRODUCT_ORDER);
                    ps.setInt(1, productOrder.getOrderedQuantity());
                    ps.setInt(2, id);
                    ps.executeQuery();
                    System.out.println("Order is updated!");
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
