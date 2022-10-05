package Dao;

import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Helpers.Queries.SQLCustomerQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.Customer;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CustomerDao extends DatabaseManager implements CrudDao<Customer>, SQLCustomerQueries, SQLQueries {
    String tableName = "customers";
    ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<>();


    @Override
    public void save(Customer customer) {
        executeQuery(String.format(SAVE_CUSTOMER, customer.toQuery()));
    }

    /**
     * delete() will delete a customer by given customerId
     */
    @Override
    public void delete(int id) {
        executeUpdate(String.format(DELETE_RECORD, tableName, id));
    }


    /**
     * deleteAll() will truncate a table by given table name
     */
    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));
    }


    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(String status, int customerId) {
        if (Objects.equals(status, "activate")) {
            executeQuery(String.format(ACTIVATE_CUSTOMER, customerId));
        } else {
            executeQuery(String.format(DEACTIVATE_CUSTOMER, customerId));
        }
    }

    @Override
    public int getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        return resultSetMapper.mapResultSetToInt(rs);
    }


    /**
     * getByIdReflection() method will get customer by id
     * and map it to Customer.Class using reflection.
     */
    public Customer getByIdReflection(int id) {
        Customer customer = null;

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    customer = mapResultSetToReflection(rs);
                }
            } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    /**
     * This method will map ResultSet to a Customer object using a Reflection method
     */
    public Customer mapResultSetToReflection(ResultSet rs) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer();

        Field customerIdField = customer.getClass()
                .getDeclaredField("customerId");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, rs.getString("customer_id"));

        Field nameField = customer.getClass()
                .getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(customer, rs.getString("name"));

        Field emailField = customer.getClass()
                .getDeclaredField("email");
        emailField.setAccessible(true);
        emailField.set(customer, rs.getString("email"));

        Field phoneField = customer.getClass()
                .getDeclaredField("phone");
        phoneField.setAccessible(true);
        phoneField.set(customer, rs.getString("phone"));

        Field ageField = customer.getClass()
                .getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(customer, rs.getInt("age"));

        Field gdprField = customer.getClass()
                .getDeclaredField("gdpr");
        gdprField.setAccessible(true);
        gdprField.setBoolean(customer, rs.getBoolean("gdpr"));

        Field customerProfileStatusField = customer.getClass()
                .getDeclaredField("customerProfileStatus");
        customerProfileStatusField.setAccessible(true);
        customerProfileStatusField.setBoolean(customer, rs.getBoolean("customer_profile_status"));

        Field deactivationDateField = customer.getClass()
                .getDeclaredField("deactivationDate");
        deactivationDateField.setAccessible(true);
        deactivationDateField.set(customer, rs.getDate("deactivation_date"));

        Field reasonField = customer.getClass()
                .getDeclaredField("reason");
        reasonField.setAccessible(true);
        reasonField.set(customer, rs.getString("reason"));

        Field notesField = customer.getClass()
                .getDeclaredField("notes");
        notesField.setAccessible(true);
        notesField.set(customer, rs.getString("notes"));


        Field activationDateField = customer.getClass()
                .getDeclaredField("activationDate");
        activationDateField.setAccessible(true);
        activationDateField.set(customer, rs.getDate("activation_date"));

        return customer;
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
     * getByID() method will get customer by id
     * and map it to Customer.Class using DbUtils with custom handler.
     */

    @Override
    public Customer getByID(int id) {
        return resultSetMapper.mapResultSetToObject(executeQuery(String.format(GET_BY_ID, tableName, id)), Customer.class);
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override
    public List<Customer> getByIDs(List<Integer> ids) {
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        List<Customer> customers = resultSetMapper.mapResultSetToObjects(executeQuery(String.format(GET_BY_IDS, tableName, joiner)), Customer.class);
        return customers;
    }

    @Override
    public void truncate() {
        executeUpdate(String.format(TRUNCATE_TABLE, tableName));
    }


    /**
     * getByIdDbUtils() method will get customers by ids
     * and will return a Customer
     * using DbUtils with custom handler.
     */
    //Override
    public Customer getByIdDbUtils(int id) {
        Customer customer = (Customer) getByIdDbUtils(String.format(GET_BY_ID, tableName, id), new BeanHandler<>(Customer.class));
        return customer;
    }


    /**
     * getByIdsDbUtils() method will get List of customers by ids
     * using DbUtils with custom handler.
     */
    public List<Customer> getByIdsDbUtils(List<Integer> ids) {
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        List<Customer> objects = getByIdsDbUtils(String.format(GET_BY_IDS, tableName, joiner), new BeanListHandler<>(Customer.class));
        return objects;
    }
}

