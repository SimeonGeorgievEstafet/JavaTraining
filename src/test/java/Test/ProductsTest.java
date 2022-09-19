package Test;

import Dao.ProductsDao;
import Helpers.ProductsHelper;
import POJO.Product;

import java.util.List;

public class ProductsTest {
    public static void main(String[] args) {
        ProductsDao productsDao = new ProductsDao();
//        productsDao.getRecordsCount();
        Product product = new ProductsHelper().CreateObject();
//
//        productsDao.save(product);
//        productsDao.getByID(68);
////        productsDao.delete(68);
//        productsDao.update(new ProductsHelper().CreateObject(), 1);
            productsDao.deleteAll();
//        List<Product> productList = new ProductsHelper().CreateObjects(5);
    }
}
