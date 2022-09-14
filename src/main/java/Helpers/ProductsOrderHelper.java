package Helpers;

import Databases.DatabaseManager;
import POJO.ProductOrder;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductsOrderHelper {

    Faker faker = new Faker();

    /**
     * CreateProduct method will user Faker to create valid data forProductOrders.
     * The data will be printed and returned as Product object.
     */
    public ProductOrder CreateProductOrder() {
        ProductOrder productOrder = ProductOrder.builder()
                .orderId(String.valueOf(faker.random().nextInt(1021, 1104)))
                .productId(String.valueOf(faker.random().nextInt(19, 120)))
                .orderedQuantity(faker.random().nextInt(1, 100))
                .build();
        System.out.println(productOrder);
        return productOrder;
    }

    /**
     * CreateProductOrder method will use user data to create ProductOrders.
     */
    public ProductOrder CreateProductOrder(int a, int b, int c) {
        ProductOrder productorder = ProductOrder.builder()
                .orderId(String.valueOf(a))
                .productId(String.valueOf(b))
                .orderedQuantity(faker.random().nextInt(1, 100))
                .build();
        System.out.println(productorder);
        return productorder;
    }


    /**
     * CreateProductOrders method will user Faker to create valid list of ProductOrders.
     */
    public List<ProductOrder> CreateProductOrders(int numberOfProductOrders) {
        List<ProductOrder> productOrderList = new ArrayList<>();
        for (int i = 0; i < numberOfProductOrders; i++) {
            productOrderList.add(CreateProductOrder());
        }
        System.out.println(productOrderList);
        return productOrderList;
    }
}
