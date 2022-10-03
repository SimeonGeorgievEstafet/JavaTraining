package Dao;

import Helpers.Queries.SQLOrderQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.Order;

import java.sql.ResultSet;
import java.util.List;
import java.util.StringJoiner;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class OrderDao implements CrudDao<Order>, SQLOrderQueries, SQLQueries {

    ResultSetMapper<Order> resultSetMapper = new ResultSetMapper<>();
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
    public Order getByID(int id) {
        return resultSetMapper.mapResultSetToObject(executeQuery(String.format(GET_BY_ID, tableName, id)), Order.class);
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override
    public List<Order> getByIDs(List<Integer> ids) {
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        return resultSetMapper.mapResultSetToObjects(executeQuery(String.format(GET_BY_IDS, tableName, joiner)), Order.class);
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        executeUpdate(String.format(DELETE_RECORD, tableName, id));
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
    public void truncate() {
        executeUpdate(String.format(TRUNCATE_TABLE,tableName));
    }
    @Override
    public int getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        System.out.println(resultSetMapper.mapResultSetToInt(rs));
        return 0;
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
