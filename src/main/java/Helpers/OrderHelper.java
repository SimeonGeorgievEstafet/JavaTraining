package Helpers;

import Dao.CrudDao;
import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Handlers.OrderHandler;
import POJO.Order;
import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHelper implements CrudDao {

    DatabaseManager dbm = new DatabaseManager();

    /**
     * CreateProduct method will user Faker to create valid data for product.
     * The data will be printed and returned as Product object.
     */
    public Order CreateOrder() {
        Faker faker = new Faker();
        Order order = Order.builder()
                .customerId(faker.random().nextInt(42,68))
                .isOrderCompleted(Boolean.FALSE)
                .isOrderPaid(faker.random().nextBoolean())
                .build();
        System.out.println(order);
        return order;
    }

    /**
     * Method save() will get created order and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and order will be saved in DB.
     */
    @Override
    public void save(Object object) {
        dbm.save(object,SQLQueries.SAVE_ORDER);
    }

    /**
     * getById() method will get order by id
     * and map it to Order.Class using DbUtils with custom handler.
     */
    @Override
    public Order getByID(int id) {
        return (Order) dbm.getByID(id,SQLQueries.GET_ORDER_BY_ID,new OrderHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int orderId) {
        dbm.delete(orderId,SQLQueries.DELETE_ORDER);
    }

    /**
     * Method update() will make order paid by order id.
     */
    public void update(int id) {
        dbm.update(new Order(),SQLQueries.UPDATE_ORDER,id);
    }
}
