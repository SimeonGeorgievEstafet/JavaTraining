package Test;

import Dao.OrderDao;
import Helpers.OrderHelper;
import POJO.Order;

import java.util.List;

public class OrdersTest {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
//        Order order = orderDao.getByID(3);
//        orderDao.getByID(3);

//        Order order2 = new OrderHelper().CreateObject();
//        orderDao.save(order2);
//
//        orderDao.delete(1112);
//        orderDao.update(3);
        orderDao.deleteAll();

//        orderDao.getProductOrdersByOrderId(1065);
//            orderDao.getRecordsCount();
//        List<Order> orderList = new OrderHelper().CreateObjects(3);
    }
}
