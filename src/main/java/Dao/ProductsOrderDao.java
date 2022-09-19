package Dao;

import Databases.DatabaseManager;
import Handlers.ProductOrderHandler;
import Helpers.Queries.SQLProductOrdersQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.ProductOrder;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class ProductsOrderDao implements CrudDao<ProductOrder>, SQLProductOrdersQueries, SQLQueries {

    DatabaseManager databaseManager = new DatabaseManager();
    ResultSetMapper<Integer> resultSetMapper = new ResultSetMapper<>();
    String tableName = "product_orders";

    /**
     * Method save() will get created product and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and product will be saved in DB.
     */
    @Override
    public void save(ProductOrder productOrder) {
        executeQuery(String.format(SAVE_PRODUCT_ORDER, productOrder.toQuery()));
    }

    /**
     * getById() method will get product by id
     * and map it to Product.Class using DbUtils with custom handler.
     */
    @Override
    public Object getByID(int id) {
        ProductOrderHandler productOrderHandler = new ProductOrderHandler();
        ProductOrder productOrder = (ProductOrder) databaseManager.getByID(String.format(GET_BY_ID, tableName, id), productOrderHandler);
        System.out.println(productOrder.getId());
        return productOrder;
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override
    public List<Object> getByIDs(List<Integer> ids) {
        ProductOrderHandler productOrderHandler = new ProductOrderHandler();
        List<Object> productList = new ArrayList<>();
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        String query = String.format(GET_BY_IDS, tableName, joiner);

        databaseManager.getByIDs(query, productOrderHandler);
        System.out.println(productList);
        return productList;
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        databaseManager.delete(id, DELETE_PRODUCT_ORDER);
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));

    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(ProductOrder productOrder, int id) {
        executeQuery(String.format(UPDATE_RECORD, tableName, productOrder.toString(), id));
    }

    @Override
    public void getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        System.out.println(resultSetMapper.mapResultSetToInt(rs));
    }

    @Override
    public int getRandomId() {
        int id;
        {
            ResultSet rs = executeQuery(String.format(GET_RANDOM_ID, tableName));
            id = resultSetMapper.mapResultSetToInt(rs);
            System.out.println(id);
        }
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
