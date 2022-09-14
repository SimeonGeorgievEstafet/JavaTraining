package Dao;

import Databases.DatabaseManager;
import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Handlers.CustomerAddressHandler;
import Handlers.CustomerHandler;
import Handlers.OrderHandler;
import Helpers.ResultSetMapper;
import Helpers.SQLQueries;
import POJO.Customer;
import POJO.CustomerAddress;
import POJO.Order;
import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDao extends DatabaseManager implements CrudDao<Customer>, SQLQueries {

    DatabaseManager dbm = new DatabaseManager();

    /**
     * //     * Method save() will get created customer and will prepare a
     * //     * SQL statement with correct parameters. After that the Query
     * //     * will be executed and customer will be saved in DB.
     * //
     */
    @Override
    public void save(Customer customer) {
        executeQuery(String.format(SAVE_CUSTOMER, customer.toString()));
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
    public void deleteAll(String database) {
        executeQuery(String.format(DELETE_ALL_USERS, database));
    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    public void update(String status, int customerId) {
        if (Objects.equals(status, "activate")) {
            dbm.update(new Customer(), SQLQueries.ACTIVATE_CUSTOMER, customerId);
        } else {
            dbm.update(new Customer(), SQLQueries.DEACTIVATE_CUSTOMER, customerId);
        }
    }
//
//    /**
//     * delete() will delete a customer by given customerId
//     */
//    @Override
//    public void delete(int customerId) {
//        dbm.delete(customerId, SQLQueries.DELETE_CUSTOMER);
//    }

    /**
     * This method will get random customer from the DB and will return customerId
     */
    public int getRandomId() {
        int CustomerId = 0;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_RANDOM_CUSTOMER);
            try {
                if (rs.next()) {
                    CustomerId = Integer.parseInt(rs.getString(1));
                    System.out.println(CustomerId);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CustomerId;
    }

    /**
     * getByID() method will get customer by id,
     * will map the values from result set to Customer object
     * and will return Customer.
     */
    @Override
    public Customer getByID(int id) {
        Customer customer = null;

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_CUSTOMER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    customer = mapResultSetToCustomer(rs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customer);
        return customer;
    }

    /**
     * Method getByIDs() will get a list of customers,
     * then it will find them in the DB and will return a list of customers.
     */
    public List<Customer> getByIDs(List<Integer> ids) {

        List<Customer> customersList = new ArrayList<>();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            //Next logic will create as many "?" as the size of the list "ids" is.
            StringBuilder builder = new StringBuilder();
            builder.append("?,".repeat(ids.size()));
            String placeHolders = builder.deleteCharAt(builder.length() - 1).toString();
            String query = SQLQueries.GET_CUSTOMER_BY_IDS.replace("?", placeHolders);

            //Put all the values in the Query
            PreparedStatement ps = conn.prepareStatement(query);
            int index = 1;
            for (Object o : ids) {
                ps.setObject(index++, o);
            }

            //Executing the Query
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    Customer customer = mapResultSetToCustomer(rs);
                    customersList.add(customer);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customersList);
        return customersList;
    }


    /**
     * getRecordsCount will return the total count of all customers.
     */
    public int getRecordsCount() {
        int CustomerId = 0;
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_RECORD_COUNT);
            try {
                if (rs.next()) {
                    CustomerId = Integer.parseInt(rs.getString(1));
                    System.out.println(CustomerId);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CustomerId;
    }


    /**
     * getRandomIds() method will get a number as Integer,
     * after that it will execute QUERY with that number.
     * The result of the QUERY will be a String with randomIDs from the DB.
     * After set of transformation the method will return ArrayList with random IDs.
     */
    public ArrayList<Integer> getRandomIds(int j) {
        ArrayList<Integer> customerIds = new ArrayList<>();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_RANDOM_IDS);
            ps.setInt(1, j);
            ResultSet rs = ps.executeQuery();
            String result;
            //transforming result to ArrayList
            try {
                if (rs.next()) {
                    result = rs.getString(1);
                    result = result.replace("{", "");
                    result = result.replace("}", "");

                    String[] convertedResultArray = result.split(",");
                    for (String number : convertedResultArray) {
                        customerIds.add(Integer.parseInt(number.trim()));
                    }
                    System.out.println(result);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerIds;
    }

//    /**
//     * This method will truncate the table customers_1
//     */
//    public void deleteAll() {
//        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
//            Statement stmt = conn.createStatement();
//            stmt.execute(SQLQueries.DELETE_ALL_USERS);
//            System.out.println("Table customers_1 was deleted");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    /**
     * This implementation is using ResultSetMapper instead of manually
     * mapping columns from the DB to the Customer object.
     */
    public List<Customer> getByIdResultSetMapper(List<Integer> ids) {
        List<Customer> customersList = new ArrayList<>();
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();

            //Next logic will create as many "?" as the size of the list "ids" is.
            StringBuilder builder = new StringBuilder();
            builder.append("?,".repeat(ids.size()));
            String placeHolders = builder.deleteCharAt(builder.length() - 1).toString();
            String query = SQLQueries.GET_CUSTOMER_BY_IDS.replace("?", placeHolders);

            //Put all the values in the Query
            PreparedStatement ps = conn.prepareStatement(query);
            int index = 1;
            for (Object o : ids) {
                ps.setObject(index++, o);
            }
            //Executing the Query
            ResultSet rs = ps.executeQuery();

            //Map te result with resultSetMapper to Customer.class
            customersList = resultSetMapper.mapResultSetToObject(rs, Customer.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customersList);
        return customersList;
    }

    /**
     * getByIdResultSetMapper() method will get customer by id
     * and map it to Customer.Class using ResultSetMapper.
     */
    public Customer getByIdResultSetMapper(int id) {
        Customer customer = null;
        ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_CUSTOMER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Map te result with resultSetMapper to Customer.class
            List<Customer> customersList = new ArrayList<>();
            customersList = resultSetMapper.mapResultSetToObject(rs, Customer.class);
            customer = customersList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customer);
        return customer;
    }

    /**
     * getByIdReflection() method will get customer by id
     * and map it to Customer.Class using reflection.
     */
    public Customer getByIdReflection(int id) {
        Customer customer = null;

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_CUSTOMER_BY_ID);
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
     * This method will map ResultSet to a Customer object
     */
    public Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return Customer.builder().
                customerId(rs.getString("customer_id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .age(rs.getInt("age"))
                .gdpr(rs.getBoolean("gdpr"))
                .customerProfileStatus(rs.getBoolean("customer_profile_status"))
                .deactivationDate(rs.getDate("deactivation_date"))
                .reason(rs.getString("reason"))
                .notes(rs.getString("notes"))
                .activationDate(rs.getDate("activation_date"))
                .build();
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
     * getByIdDbUtils() method will get customer by id
     * and map it to Customer.Class using DbUtils with custom handler.
     */
    public Customer getByIdDbUtils(int id) {
        CustomerHandler ch = new CustomerHandler();
        return (Customer) dbm.getByID(id, SQLQueries.GET_CUSTOMER_BY_ID, ch);
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
            String query = SQLQueries.GET_CUSTOMER_BY_IDS.replace("?", placeHolders);

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
                customerAddressList = queryRunner.query(conn, SQLQueries.GET_CUSTOMER_ADDRESS_BY_ID, cah, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(customerAddressList.get(0));
            return customerAddressList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getCustomerAddress() method will get customer address.
     */
    public List<Order> getCustomerOrders(int id) {
        OrderHandler oh = new OrderHandler();
        List<Order> orderList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                orderList = queryRunner.query(conn, SQLQueries.GET_ALL_CUSTOMER_ORDERS, oh, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(orderList);
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

