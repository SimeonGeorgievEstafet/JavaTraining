package Helpers;

import POJO.Customer;
import POJO.CustomerAddress;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddressHelper {

    Faker faker = new Faker();

    /**
     * CreateCustomerAddress method will user Faker to create valid data for customer.
     * The data will be printed and returned as CustomerAddress object.
     */
    public CustomerAddress CreateCustomerAddress() {
        CustomerAddress customerAddress = CustomerAddress.builder()
                .address(faker.address().fullAddress())
                .city(faker.address().city())
                .province(faker.address().secondaryAddress())
                .state(faker.address().state())
                .postalCode(faker.random().nextInt(10000, 99999))
                .country(faker.address().country())
                .build();
        System.out.println(customerAddress);
        return customerAddress;
    }

    /**
     * CreateCustomerAddress method will user Faker to create valid data list of CustomerAddress.
     * The data will be printed and returned as CustomerAddress object.
     */
    public List<CustomerAddress> CreateCustomerAddresses(int numberOfCustomerAddresses) {
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        for (int i = 0; i < numberOfCustomerAddresses; i++) {
            CustomerAddress customerAddress = CustomerAddress.builder()
                    .address(faker.address().fullAddress())
                    .city(faker.address().city())
                    .province(faker.address().secondaryAddress())
                    .state(faker.address().state())
                    .postalCode(faker.random().nextInt(10000, 99999))
                    .country(faker.address().country())
                    .build();
            customerAddressList.add(customerAddress);
        }
        System.out.println(customerAddressList);
        return customerAddressList;
    }
}
