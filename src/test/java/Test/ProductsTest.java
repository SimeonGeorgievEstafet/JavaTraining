package Test;

import Dao.ProductsDao;
import Helpers.ProductsHelper;
import POJO.Product;

import java.util.List;

public class ProductsTest {
    public static void main(String[] args) {
        ProductsDao productsDao = new ProductsDao();
        Product product = new ProductsHelper().CreateObject();
        productsDao.save(product);
        productsDao.delete(productsDao.getRandomId());
        productsDao.update(new ProductsHelper().CreateObject(), productsDao.getRandomId());
        productsDao.deleteAll();

        //NEW
        productsDao.getRecordsCount();
        productsDao.getRandomId();
        productsDao.getRandomIds(6);
        productsDao.getByID(productsDao.getRandomId());
        productsDao.getByIDs(productsDao.getRandomIds(5));
    }
}
