package Test;

import Dao.ProductsDao;
import Helpers.ProductsHelper;
import POJO.Product;

import java.util.List;

public class ProductsTest {
    public static void main(String[] args) {
        ProductsDao productsDao = new ProductsDao();
        Product product = new ProductsHelper().CreateProduct();

        productsDao.save(product);
        productsDao.getByID(68);
//        productsDao.delete(68);
        productsDao.update(new ProductsHelper().CreateProduct(),1);

        List<Product> productList = new ProductsHelper().CreateProducts(5);
    }
}
