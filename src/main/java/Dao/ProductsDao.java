package Dao;

import Databases.DatabaseManager;
import Handlers.ProductHandler;
import Helpers.SQLQueries;
import POJO.Product;

public class ProductsDao implements CrudDao<Product> {

    DatabaseManager dbm = new DatabaseManager();


    /**
     * Method save() will get created product and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and product will be saved in DB.
     */
    @Override
    public void save(Product object) {
        Product product = (Product) object;
        dbm.save(product, SQLQueries.SAVE_PRODUCT);
    }

    /**
     * getById() method will get product by id
     * and map it to Product.Class using DbUtils with custom handler.
     */
    @Override
    public Product getByID(int id) {
        return (Product) dbm.getByID(id, SQLQueries.GET_PRODUCT_BY_ID, new ProductHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        dbm.delete(id, SQLQueries.DELETE_PRODUCT);
    }

    @Override
    public void deleteAll(String database) {

    }

    /**
     * Method update() will update Product by id.
     */
    public void update(Product product, int productId) {
        dbm.update(product, SQLQueries.UPDATE_PRODUCT, productId);
    }
}
