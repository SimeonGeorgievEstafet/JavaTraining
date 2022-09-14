package Helpers;

import Databases.DatabaseManager;
import POJO.Order;
import POJO.ProductOrder;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper {

    Faker faker = new Faker();

    /**
     * CreateOrder method will user Faker to create valid data for Order.
     * The data will be printed and returned as Order object.
     */
    public Order CreateOrder() {
        Order order = Order.builder()
                .customerId(faker.random().nextInt(1001,1119))
                .isOrderCompleted(Boolean.FALSE)
                .isOrderPaid(faker.random().nextBoolean())
                .build();
        System.out.println(order);
        return order;
    }

    /**
     * CreateOrders method will user Faker to create valid list of Orders.
     */
    public List<Order> CreateOrders(int numberOfOrders) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orderList.add(CreateOrder());
        }
        System.out.println(orderList);
        return orderList;
    }
}
