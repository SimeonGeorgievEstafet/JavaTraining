package Test;

import Helpers.OrderHelper;
import POJO.Order;

public class OrdersTest {
    public static void main(String[] args) {
        OrderHelper oh = new OrderHelper();
        oh.getByID(28);

        Order order = oh.CreateOrder();
        oh.save(order);

        oh.delete(28);
        oh.update(14);

    }
}
