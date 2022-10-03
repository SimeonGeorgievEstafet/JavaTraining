package Helpers;

import POJO.Order;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper implements ObjectCreator<Order> {

    Faker faker = new Faker();

    /**
     * CreateOrder method will user Faker to create valid data for Order.
     * The data will be printed and returned as Order object.
     */
    public Order CreateObject() {
        Order order = Order.builder()
                .isOrderCompleted(Boolean.FALSE)
                .isOrderPaid(faker.random().nextBoolean())
                .build();
        System.out.println(order);
        return order;
    }

    /**
     * CreateOrders method will user Faker to create valid list of Orders.
     */
    public List<Order> CreateObjects(int numberOfOrders) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orderList.add(CreateObject());
        }
        System.out.println(orderList);
        return orderList;
    }
}
