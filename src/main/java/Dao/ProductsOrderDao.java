package Dao;

import Databases.DatabaseManager;
import Handlers.ProductOrderHandler;
import Helpers.Queries.SQLProductOrdersQueries;
import Helpers.Queries.SQLQueries;
import POJO.ProductOrder;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class ProductsOrderDao implements CrudDao<ProductOrder>, SQLProductOrdersQueries, SQLQueries {

    DatabaseManager dbm = new DatabaseManager();

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
    public ProductOrder getByID(int id) {
        return (ProductOrder) dbm.getByID(id, GET_PRODUCT_ORDER_BY_ID, new ProductOrderHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        dbm.delete(id, DELETE_PRODUCT_ORDER);
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));

    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(ProductOrder productOrder, int id) {
        executeQuery(String.format(UPDATE_RECORD, "product_orders", productOrder.toString(), id));
    }

    @Override
    public void getRecordsCount() {
        executeQuery(String.format(GET_RECORD_COUNT, "product_orders"));
    }

}
