package Dao;

import Databases.DatabaseManager;
import Handlers.ProductHandler;
import Helpers.Queries.SQLProductQueries;
import Helpers.Queries.SQLQueries;
import POJO.Product;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class ProductsDao implements CrudDao<Product>, SQLProductQueries, SQLQueries {

    DatabaseManager dbm = new DatabaseManager();


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
        return (Product) dbm.getByID(id, GET_PRODUCT_BY_ID, new ProductHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        dbm.delete(id, DELETE_PRODUCT);
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));

    }

    /**
     * Method update() will update Product by id.
     */
    public void update(Product product, int id) {
        executeQuery(String.format(UPDATE_RECORD, "products_inventory", product.toString(), id));
    }

    @Override
    public void getRecordsCount() {
        executeQuery(String.format(GET_RECORD_COUNT, "products"));
    }
}
