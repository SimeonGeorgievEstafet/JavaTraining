package Dao;

import Databases.DatabaseManager;
import Handlers.ProductOrderHandler;
import Helpers.SQLQueries;
import POJO.ProductOrder;

import static Databases.DatabaseManager.executeQuery;

public class ProductsOrderDao implements CrudDao<ProductOrder>,SQLQueries {

    DatabaseManager dbm = new DatabaseManager();

    /**
     * Method save() will get created product and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and product will be saved in DB.
     */
    @Override
    public void save(ProductOrder object) {
        dbm.save(object, SQLQueries.SAVE_PRODUCT_ORDER);
    }

    /**
     * getById() method will get product by id
     * and map it to Product.Class using DbUtils with custom handler.
     */
    @Override
    public ProductOrder getByID(int id) {
        return (ProductOrder) dbm.getByID(id, SQLQueries.GET_PRODUCT_ORDER_BY_ID, new ProductOrderHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        dbm.delete(id, SQLQueries.DELETE_PRODUCT_ORDER);
    }

    @Override
    public void deleteAll(String database) {

    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(ProductOrder productOrder, int id) {
        dbm.update(productOrder, SQLQueries.UPDATE_PRODUCT_ORDER, id);
    }

    @Override
    public void getRecordsCount() {
        executeQuery(String.format(GET_RECORD_COUNT, "product_orders"));
    }

}
