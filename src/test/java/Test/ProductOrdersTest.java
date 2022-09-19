package Test;

import Dao.ProductsOrderDao;
import Helpers.ProductsOrderHelper;
import POJO.ProductOrder;

public class ProductOrdersTest {
    public static void main(String[] args) {
        ProductsOrderDao productsOrderDao = new ProductsOrderDao();

        ProductOrder productOrder = new ProductsOrderHelper().CreateObject();
        productsOrderDao.save(productOrder);
        productsOrderDao.delete(productsOrderDao.getRandomId());
        productOrder.setOrderedQuantity(productsOrderDao.getRandomId());
        productsOrderDao.update(productOrder, productsOrderDao.getRandomId());
        productsOrderDao.deleteAll();

        //New
        productsOrderDao.getRecordsCount();
        productsOrderDao.getRandomId();
        productsOrderDao.getRandomIds(4);
        productsOrderDao.getByID(productsOrderDao.getRandomId());
        productsOrderDao.getByIDs(productsOrderDao.getRandomIds(5));
    }
}
