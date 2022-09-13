package Helpers;

import Dao.CrudDao;
import Databases.DatabaseManager;
import Handlers.CustomerAddressHandler;
import POJO.CustomerAddress;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAddressHelper implements CrudDao {
    DatabaseManager dbm = new DatabaseManager();

    /**
     * CreateCustomerAddress method will user Faker to create valid data for customer.
     * The data will be printed and returned as CustomerAddress object.
     */
    public CustomerAddress CreateCustomerAddress() {
        Faker faker = new Faker();
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
     * Mehod save() will get created customer and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and customer will be saved in DB.
     */
    @Override
    public void save(Object object) {
        dbm.save(object,SQLQueries.SAVE_CUSTOMER_ADDRESS);
    }

    /**
     * getById() method will get customer by id
     * and map it to CustomerAddress.Class using DbUtils with custom handler.
     */
    @Override
    public CustomerAddress getByID(int id) {
        return (CustomerAddress) dbm.getByID(id, SQLQueries.GET_CUSTOMER_ADDRESS_BY_ID, new CustomerAddressHandler());
    }


    /**
     * delete() will delete a customer address by given customerAddressId
     */
    @Override
    public void delete(int customerAddressId) {
        dbm.delete(customerAddressId, SQLQueries.DELETE_CUSTOMER_ADDRESS);
    }

    /**
     * Method update() will update customer address by given customerAddressId.
     */
    public void update(CustomerAddress customerAddress, int customerAddressId) {
        dbm.update(customerAddress, SQLQueries.UPDATE_CUSTOMER_ADDRESS, customerAddressId);
    }
}
