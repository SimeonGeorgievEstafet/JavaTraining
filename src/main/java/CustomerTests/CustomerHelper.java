package CustomerTests;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomerHelper implements CustomerDao {

    /**
     * CreateCustomer method will user Faker to create valid data for customer.
     * The data will be printed and returned as Customer object.
     */
    public Customer CreateCustomer() {
        Faker faker = new Faker();
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
     * Mehod save() will get created customer and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and customer will be saved in DB.
     */
    @Override
    public void save(Customer customer) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.SAVE_CUSTOMER);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getAge());
            ps.setBoolean(5, customer.isGdpr());
            ps.setBoolean(6, customer.isCustomerProfileStatus());
            ps.setString(7, customer.getReason());
            ps.setString(8, customer.getNotes());
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            // Extract data from result set
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            while (rs.next()) {
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method update() will activate or deactivate customer by given customerId.
     */
    @Override
    public void update(String status, int customerId) {
        if (Objects.equals(status, "activate")) {
            try (Connection conn = DatabaseSingletonHelper.getInstance()) {
                PreparedStatement ps = conn.prepareStatement(SQLQueries.ACTIVATE_CUSTOMER);
                ps.setInt(1, customerId);
                ps.executeQuery();
                System.out.println("Customer is active!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection conn = DatabaseSingletonHelper.getInstance()) {
                PreparedStatement ps = conn.prepareStatement(SQLQueries.DEACTIVATE_CUSTOMER);
                ps.setInt(1, customerId);
                ps.executeQuery();
                System.out.println("Customer deactivated!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * delete() will delete a customer by given customerId
     */
    @Override
    public void delete(int customerId) {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.DELETE_USER);
            ps.setInt(1, customerId);
            ps.executeQuery();
            System.out.println("Customer with id = " + customerId + " was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will get random customer from the DB and will return customerId
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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

    /**
     * This method will truncate the table customers_1
     */
    @Override
    public void deleteAll() {
        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            Statement stmt = conn.createStatement();
            stmt.execute(SQLQueries.DELETE_ALL_USERS);
            System.out.println("Table customers_1 was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This implementation is using ResultSetMapper instead of manually
     * mapping columns from the DB to the Customer object.
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Customer getByIdDbUtils(int id) {

        CustomerHandler ch = new CustomerHandler();
        List<Customer> customerList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();

        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
            try {
                customerList = queryRunner.query(conn, SQLQueries.GET_CUSTOMER_BY_ID, ch, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(customerList.get(0));
            return customerList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getByIdsDbUtils() method will get customers by ids
     * and will return a list with Customers
     * using DbUtils with custom handler.
     */
    @Override
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
}
