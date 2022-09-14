package Helpers;

import POJO.Customer;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CustomerHelper implements ObjectCreator<Customer>{

    Faker faker = new Faker();

    /**
     * CreateCustomer method will user Faker to create valid data for customer.
     * The data will be printed and returned as Customer object.
     */
    public Customer CreateObject() {
        Customer customer = Customer.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .age(faker.random().nextInt(18, 99))
                .gdpr(faker.random().nextBoolean())
                .customerProfileStatus(faker.random().nextBoolean())
                .reason(faker.lorem().fixedString(10))
                .notes(faker.lorem().fixedString(10))
                .build();
        System.out.println(customer);
        return customer;
    }

    /**
     * CreateCustomer method will user Faker to create valid list of customers.
     */
    public List<Customer> CreateObjects(int numberOfCustomers) {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < numberOfCustomers; i++) {
            customerList.add(CreateObject());
        }
        System.out.println(customerList);
        return customerList;
    }
}

