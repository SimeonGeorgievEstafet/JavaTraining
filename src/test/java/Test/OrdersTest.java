package Test;

import Dao.OrderDao;
import Helpers.OrderHelper;
import POJO.Order;

public class OrdersTest {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        Order order = new OrderHelper().CreateObject();
        orderDao.truncate();
        orderDao.save(order);
//        orderDao.delete(orderDao.getRandomId());
//        orderDao.update(orderDao.getRandomId());
//        orderDao.deleteAll();
//        orderDao.getRecordsCount();
//        orderDao.getRandomId();
//        orderDao.getRandomIds(4);
//
//        //NEW
//        orderDao.getByID(orderDao.getRandomId());
//        orderDao.getByIDs(orderDao.getRandomIds(5));

    }
}
