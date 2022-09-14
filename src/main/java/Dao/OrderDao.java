package Dao;

import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Handlers.OrderHandler;
import Handlers.ProductOrderHandler;
import Helpers.SQLQueries;
import POJO.Order;
import POJO.ProductOrder;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements CrudDao<Order> {

    DatabaseManager dbm = new DatabaseManager();


    /**
     * Method save() will get created order and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and order will be saved in DB.
     */
    @Override
    public void save(Order object) {
        dbm.save(object, SQLQueries.SAVE_ORDER);
    }

    /**
     * getById() method will get order by id
     * and map it to Order.Class using DbUtils with custom handler.
     */
    @Override
    public Order getByID(int id) {
        return (Order) dbm.getByID(id, SQLQueries.GET_ORDER_BY_ID, new OrderHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int orderId) {
        dbm.delete(orderId, SQLQueries.DELETE_ORDER);
    }

    @Override
    public void deleteAll(String database) {

    }

    /**
     * Method update() will make order paid by order id.
     */
    public void update(int id) {
        dbm.update(new Order(), SQLQueries.UPDATE_ORDER, id);
    }


    public List<ProductOrder> getProductOrdersByOrderId(int id) {
        List<ProductOrder> productOrders = new ArrayList<>();
        ProductOrderHandler poh = new ProductOrderHandler();
        QueryRunner queryRunner = new QueryRunner();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                productOrders = queryRunner.query(conn, SQLQueries.GET_PRODUCT_ORDER_BY_ORDER_ID, poh, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(productOrders);
            return productOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
