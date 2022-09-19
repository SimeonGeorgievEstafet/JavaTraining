package Dao;

import Databases.DatabaseManager;
import Handlers.OrderHandler;
import Helpers.Queries.SQLOrderQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class OrderDao implements CrudDao<Order>, SQLOrderQueries, SQLQueries {

    DatabaseManager databaseManager = new DatabaseManager();
    ResultSetMapper<Integer> resultSetMapper = new ResultSetMapper<>();
    String tableName = "orders";

    /**
     * Method save() will get created order and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and order will be saved in DB.
     */
    @Override
    public void save(Order order) {
        executeQuery(String.format(SAVE_ORDER, order.toQuery()));

    }

    /**
     * getById() method will get order by id
     * and map it to Order.Class using DbUtils with custom handler.
     */
    @Override
    public Object getByID(int id) {
        OrderHandler orderHandler = new OrderHandler();
        return databaseManager.getByID(String.format(GET_BY_ID, tableName, id), orderHandler);
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override

    public List<Object> getByIDs(List<Integer> ids) {
        OrderHandler orderHandler = new OrderHandler();
        List<Object> orderList = new ArrayList<>();
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        String query = String.format(GET_BY_IDS, tableName, joiner);

        databaseManager.getByIDs(query, orderHandler);
        System.out.println(orderList);
        return orderList;
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int orderId) {
        databaseManager.delete(orderId, DELETE_ORDER);
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));
    }

    /**
     * Method update() will make order paid by order id.
     */
    public void update(int id) {
        executeQuery(String.format(UPDATE_RECORD, tableName, "date_of_order_completed = now()", id));
    }

    @Override
    public void getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        System.out.println(resultSetMapper.mapResultSetToInt(rs));
    }

    @Override
    public int getRandomId() {

        ResultSet rs = executeQuery(String.format(GET_RANDOM_ID, tableName));
        int id = resultSetMapper.mapResultSetToInt(rs);
        System.out.println(id);
        return id;
    }

    @Override
    public List<Integer> getRandomIds(int numberOfIds) {
        List<Integer> ids;
        ResultSet rs = executeQuery(String.format(GET_RANDOM_IDS, tableName, numberOfIds));
        ids = resultSetMapper.mapResultSetToList(rs);
        System.out.println(ids);
        return ids;
    }

}
