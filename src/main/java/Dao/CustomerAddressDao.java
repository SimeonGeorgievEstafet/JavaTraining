package Dao;

import Databases.DatabaseManager;
import Handlers.CustomerAddressHandler;
import Helpers.Queries.SQLCustomerAddressQueries;
import Helpers.Queries.SQLQueries;
import POJO.CustomerAddress;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class CustomerAddressDao implements CrudDao<CustomerAddress>, SQLCustomerAddressQueries, SQLQueries {
    DatabaseManager dbm = new DatabaseManager();


    /**
     * Mehod save() will get created customer and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and customer will be saved in DB.
     */
    @Override
    public void save(CustomerAddress customerAddress) {
        executeQuery(String.format(SAVE_CUSTOMER_ADDRESS, customerAddress.toQuery()));
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));
    }

    /**
     * getById() method will get customer by id
     * and map it to CustomerAddress.Class using DbUtils with custom handler.
     */
    @Override
    public CustomerAddress getByID(int id) {
        return (CustomerAddress) dbm.getByID(id, GET_CUSTOMER_ADDRESS_BY_ID, new CustomerAddressHandler());
    }


    /**
     * delete() will delete a customer address by given customerAddressId
     */
    @Override
    public void delete(int customerAddressId) {
        dbm.delete(customerAddressId, DELETE_CUSTOMER_ADDRESS);
    }

    /**
     * Method update() will update customer address by given customerAddressId.
     */
    public void update(CustomerAddress customerAddress, int id) {
        executeQuery(String.format(UPDATE_RECORD, "customer_addresses", customerAddress.toString(), id));
    }

    @Override
    public void getRecordsCount() {
        executeQuery(String.format(GET_RECORD_COUNT, "customer_addresses"));
    }
}
