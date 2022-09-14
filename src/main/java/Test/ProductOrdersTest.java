package Test;

import Dao.ProductsDao;
import Dao.ProductsOrderDao;
import Helpers.OrderHelper;
import Helpers.ProductsHelper;
import Helpers.ProductsOrderHelper;
import POJO.Order;
import POJO.Product;
import POJO.ProductOrder;

import java.util.List;

public class ProductOrdersTest {
    public static void main(String[] args) {
        ProductsOrderDao productsOrderDao = new ProductsOrderDao();
        productsOrderDao.getByID(25);

//        ProductOrder productOrder = new ProductsOrderHelper().CreateProductOrder();
        ProductOrder productOrder = new ProductsOrderHelper().CreateProductOrder(2,4,5);

        productsOrderDao.save(productOrder);

        productsOrderDao.delete(4);

        /*
          Update productOrder steps:
                  Get ProductOrder,
                  UpdateQuantity in ProductOrder Object,
                  pass the object for update in DB
         */
        ProductOrder productOrder2 = productsOrderDao.getByID(14);
        productOrder2.setOrderedQuantity(144);
        productsOrderDao.update(productOrder, 14);

        for (int i = 0; i < 100; i++) {
            ProductOrder product2 = new ProductsOrderHelper().CreateProductOrder();
            productsOrderDao.save(product2);
        }
        List<ProductOrder> productOrderList = new ProductsOrderHelper().CreateProductOrders(5);
    }
}
