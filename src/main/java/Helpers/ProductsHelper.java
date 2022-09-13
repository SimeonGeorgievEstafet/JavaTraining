package Helpers;

import Dao.CrudDao;
import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Handlers.ProductHandler;
import POJO.Product;
import com.github.javafaker.Faker;

import java.sql.*;

public class ProductsHelper implements CrudDao {

    DatabaseManager dbm = new DatabaseManager();

    /**
     * CreateProduct method will user Faker to create valid data for product.
     * The data will be printed and returned as Product object.
     */
    public Product CreateProduct() {
        Faker faker = new Faker();
        Product product = Product.builder()
                .productName(faker.food().fruit())
                .availableQuantity(faker.random().nextInt(1,100))
                .productType(faker.food().vegetable())
                .priceWithoutVat(faker.number().randomDouble(2,1,99))
                .priceWithVat(faker.number().randomDouble(2,1,99))
                .inStock(faker.random().nextBoolean())
                .supplier(faker.random().nextInt(1,3))
                .build();
        System.out.println(product);
        return product;
    }

    /**
     * Method save() will get created product and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and product will be saved in DB.
     */
    @Override
    public void save(Object object) {
        Product product = (Product) object;
        dbm.save(product,SQLQueries.SAVE_PRODUCT);
    }

    /**
     * getById() method will get product by id
     * and map it to Product.Class using DbUtils with custom handler.
     */
    @Override
    public Product getByID(int id) {
        return (Product) dbm.getByID(id,SQLQueries.GET_PRODUCT_BY_ID,new ProductHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int id) {
        dbm.delete(id,SQLQueries.DELETE_PRODUCT);
    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(Product product, int productId) {
        dbm.update(product,SQLQueries.UPDATE_PRODUCT,productId);
    }
}
