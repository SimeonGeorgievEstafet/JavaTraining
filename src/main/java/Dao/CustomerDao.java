package Dao;

import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Handlers.CustomerAddressHandler;
import Handlers.CustomerHandler;
import Helpers.Queries.SQLCustomerQueries;
import Helpers.Queries.SQLQueries;
import Helpers.ResultSetMapper;
import POJO.Customer;
import POJO.CustomerAddress;
import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CustomerDao extends DatabaseManager implements CrudDao<Customer>, SQLCustomerQueries, SQLQueries {
    String tableName = "customers";
    DatabaseManager databaseManager = new DatabaseManager();
    ResultSetMapper<Integer> resultSetMapper = new ResultSetMapper<>();


    @Override
    public void save(Customer customer) {
        executeQuery(String.format(SAVE_CUSTOMER, customer.toQuery()));
    }

    /**
     * delete() will delete a customer by given customerId
     */
    @Override
    public void delete(int customerId) {
        executeQuery(String.format(DELETE_CUSTOMER, customerId));
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
    public void getRecordsCount() {
        ResultSet rs = executeQuery(String.format(GET_RECORD_COUNT, tableName));
        System.out.println(resultSetMapper.mapResultSetToInt(rs));
    }


//    /**
//     * This implementation is using ResultSetMapper instead of manually
//     * mapping columns from the DB to the Customer object.
//     */
//    public List<Customer> getByIdResultSetMapper(List<Integer> ids) {
//        List<Customer> customersList = new ArrayList<>();
//        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
//            ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<>();
//
//            //Next logic will create as many "?" as the size of the list "ids" is.
//            StringBuilder builder = new StringBuilder();
//            builder.append("?,".repeat(ids.size()));
//            String placeHolders = builder.deleteCharAt(builder.length() - 1).toString();
//            String query = GET_CUSTOMER_BY_IDS.replace("?", placeHolders);
//
//            //Put all the values in the Query
//            PreparedStatement ps = conn.prepareStatement(query);
//            int index = 1;
//            for (Object o : ids) {
//                ps.setObject(index++, o);
//            }
//            //Executing the Query
//            ResultSet rs = ps.executeQuery();
//
//            //Map te result with resultSetMapper to Customer.class
//            customersList = resultSetMapper.mapResultSetToObject(rs, Customer.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(customersList);
//        return customersList;
//    }

//    /**
//     * getByIdResultSetMapper() method will get customer by id
//     * and map it to Customer.Class using ResultSetMapper.
//     */
//    public Customer getByIdResultSetMapper(int id) {
//        Customer customer = null;
//        ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();
//
//        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
//            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//
//            //Map te result with resultSetMapper to Customer.class
//            List<Customer> customersList = new ArrayList<>();
//            customersList = resultSetMapper.mapResultSetToObject(rs, Customer.class);
//            customer = customersList.get(0);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(customer);
//        return customer;
//    }

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
        System.out.println(customer);
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

        System.out.println(customer);
        return customer;
    }


    /**
     * getByIdsDbUtils() method will get customers by ids
     * and will return a list with Customers
     * using DbUtils with custom handler.
     */
    public List<Customer> getByIdsDbUtils(List<Integer> ids) {
        List<Customer> customersList = new ArrayList<>();
        CustomerHandler ch = new CustomerHandler();
        QueryRunner queryRunner = new QueryRunner();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            //Next logic will create as many "?" as the size of the list "ids" is.
            StringBuilder builder = new StringBuilder();
            builder.append("?,".repeat(ids.size()));
            String placeHolders = builder.deleteCharAt(builder.length() - 1).toString();
            String query = GET_CUSTOMER_BY_IDS.replace("?", placeHolders);

            //Put all the values in the Query
            PreparedStatement ps = conn.prepareStatement(query);
            int index = 1;
            for (Object o : ids) {
                ps.setObject(index++, o);
            }

            try {
                customersList = queryRunner.query(conn, ps.toString(), ch);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(customersList);
            return customersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getCustomerAddress() method will get customer address.
     */
    public CustomerAddress getCustomerAddress(int id) {
        CustomerAddressHandler cah = new CustomerAddressHandler();
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                customerAddressList = queryRunner.query(conn, GET_CUSTOMER_ADDRESS_BY_ID, cah, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(customerAddressList.get(0));
            return customerAddressList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println(ids);
        return ids;
    }

    /**
     * getByID() method will get customer by id
     * and map it to Customer.Class using DbUtils with custom handler.
     */
    @Override
    public Object getByID(int id) {
        return resultSetMapper.mapResultSetToObject(executeQuery(String.format(GET_BY_ID, tableName, id)), Customer.class);
    }

    /**
     * Method getByIDs() will get a list of customers,
     */
    @Override
    public List<Object> getByIDs(List<Integer> ids) {
        // join all ID-s as one string
        StringJoiner joiner = new StringJoiner(",");
        for (Integer id : ids) {
            joiner.add(String.valueOf(id));
        }
        return resultSetMapper.mapResultSetToObjects(executeQuery(String.format(GET_BY_IDS, tableName, joiner)), Customer.class);
    }

}

