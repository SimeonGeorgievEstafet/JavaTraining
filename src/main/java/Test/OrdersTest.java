package Test;

import Dao.OrderDao;
import Helpers.OrderHelper;
import POJO.Order;

import java.util.List;

public class OrdersTest {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.getByID(28);
        orderDao.getByID(1010);

        Order order2 = new OrderHelper().CreateOrder();
        orderDao.save(order2);

        orderDao.delete(28);
        orderDao.update(14);
        orderDao.getProductOrdersByOrderId(1065);

        List<Order> orderList = new OrderHelper().CreateOrders(3);
    }
}
