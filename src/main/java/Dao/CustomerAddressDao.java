package Dao;

import Databases.DatabaseManager;
import Handlers.CustomerAddressHandler;
import Helpers.SQLQueries;
import POJO.CustomerAddress;

public class CustomerAddressDao implements CrudDao<CustomerAddress> {
    DatabaseManager dbm = new DatabaseManager();


    /**
     * Mehod save() will get created customer and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and customer will be saved in DB.
     */
    @Override
    public void save(CustomerAddress object) {
        dbm.save(object, SQLQueries.SAVE_CUSTOMER_ADDRESS);
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
