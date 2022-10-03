package Dao;

import Helpers.Queries.SQLProductQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.Product;

import java.sql.ResultSet;
import java.util.List;
import java.util.StringJoiner;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class ProductsDao implements CrudDao<Product>, SQLProductQueries, SQLQueries {
    String tableName = "products_inventory";
    ResultSetMapper<Product> resultSetMapper = new ResultSetMapper<>();


    /**
     * Method save() will get created product and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and product will be saved in DB.
     */
    @Override
    public void save(Product product) {
        executeQuery(String.format(SAVE_PRODUCT, product.toQuery()));

    }

    /**
     * getById() method will get product by id
     * and map it to Product.Class using DbUtils with custom handler.
     */
    @Override
    public Product getByID(int id) {
        Product product = resultSetMapper.mapResultSetToObject(executeQuery(String.format(GET_BY_ID, tableName, id)), Product.class);
        System.out.println(product);
        return product;
    }

    /**
     * Method getByIDs() will get a list of customers,
     * then it will find them in the DB and will return a list of customers.
     */
    @Override
    public List<Product> getByIDs(List<Integer> ids) {
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        List<Product> products = resultSetMapper.mapResultSetToObjects(executeQuery(String.format(GET_BY_IDS, tableName, joiner)), Product.class);
        return products;
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

    @Override
    public void truncate() {
        executeUpdate(String.format(TRUNCATE_TABLE,tableName));
    }

    /**
     * Method update() will update Product by id.
     */
    public void update(Product product, int id) {
        executeQuery(String.format(UPDATE_RECORD, tableName, product.toString(), id));
    }

    @Override
    public int getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        System.out.println(resultSetMapper.mapResultSetToInt(rs));
        return 0;
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
