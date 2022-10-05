package Dao;

import Helpers.Queries.SQLCustomerAddressQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.CustomerAddress;

import java.sql.ResultSet;
import java.util.List;
import java.util.StringJoiner;

import static Databases.DatabaseManager.executeQuery;
import static Databases.DatabaseManager.executeUpdate;

public class CustomerAddressDao implements CrudDao<CustomerAddress>, SQLCustomerAddressQueries, SQLQueries {
    ResultSetMapper<CustomerAddress> resultSetMapper = new ResultSetMapper<>();
    String tableName = "customer_addresses";

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
        return resultSetMapper.mapResultSetToObject(executeQuery(String.format(GET_BY_ID, tableName, id)), CustomerAddress.class);
    }


    /**
     * delete() will delete a customer address by given customerAddressId
     */
    @Override
    public void delete(int id) {
        executeUpdate(String.format(DELETE_RECORD, tableName, id));
    }

    /**
     * Method update() will update customer address by given customerAddressId.
     */
    public void update(CustomerAddress customerAddress, int id) {
        executeQuery(String.format(UPDATE_RECORD, tableName, customerAddress.toString(), id));
    }

    @Override
    public int getRecordsCount() {
        return resultSetMapper.mapResultSetToInt(executeQuery(String.format(GET_RECORD_COUNT, tableName)));
    }

    @Override
    public void truncate() {
        executeUpdate(String.format(TRUNCATE_TABLE, tableName));
    }

    @Override
    public int getRandomId() {
        ResultSet rs = executeQuery(String.format(GET_RANDOM_ID, tableName));
        return resultSetMapper.mapResultSetToInt(rs);
    }

    @Override
    public List<Integer> getRandomIds(int numberOfIds) {
        List<Integer> ids;
        ResultSet rs = executeQuery(String.format(GET_RANDOM_IDS, tableName, numberOfIds));
        ids = resultSetMapper.mapResultSetToList(rs);
        return ids;
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override
    public List<CustomerAddress> getByIDs(List<Integer> ids) {
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        return resultSetMapper.mapResultSetToObjects(executeQuery(String.format(GET_BY_IDS, tableName, joiner)), CustomerAddress.class);
    }

}
