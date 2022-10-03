package Helpers;

import POJO.CustomerAddress;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddressHelper implements ObjectCreator<CustomerAddress> {

    Faker faker = new Faker();

    /**
     * CreateCustomerAddress method will user Faker to create valid data for customer.
     * The data will be printed and returned as CustomerAddress object.
     */
    public CustomerAddress CreateObject() {
        CustomerAddress customerAddress = CustomerAddress.builder()
                .address(faker.address().cityName())
                .city(faker.address().city())
                .province(faker.address().secondaryAddress())
                .state(faker.address().state())
                .postalCode(faker.random().nextInt(10000, 99999))
                .country(faker.address().country())
                .build();
        return customerAddress;
    }

    /**
     * CreateCustomerAddress method will user Faker to create valid data list of CustomerAddress.
     * The data will be printed and returned as CustomerAddress object.
     */
    public List<CustomerAddress> CreateObjects(int numberOfCustomerAddresses) {
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        for (int i = 0; i < numberOfCustomerAddresses; i++) {
            customerAddressList.add(CreateObject());
        }
        System.out.println(customerAddressList);
        return customerAddressList;
    }
}
