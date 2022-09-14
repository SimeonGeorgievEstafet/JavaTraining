package Helpers;

import Databases.DatabaseManager;
import POJO.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductsHelper {

    Faker faker = new Faker();

    /**
     * CreateProduct method will user Faker to create valid data for product.
     * The data will be printed and returned as Product object.
     */
    public Product CreateProduct() {
        Product product = Product.builder()
                .productName(faker.food().fruit())
                .availableQuantity(faker.random().nextInt(1, 100))
                .productType(faker.food().vegetable())
                .priceWithoutVat(faker.number().randomDouble(2, 1, 99))
                .priceWithVat(faker.number().randomDouble(2, 1, 99))
                .inStock(faker.random().nextBoolean())
                .supplier(faker.random().nextInt(1, 3))
                .build();
        System.out.println(product);
        return product;
    }

    /**
     * CreateProducts method will user Faker to create valid list of products.
     */
    public List<Product> CreateProducts(int numberOfProducts) {
        List<Product> productsList = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            productsList.add(CreateProduct());
        }
        System.out.println(productsList);
        return productsList;
    }

}
