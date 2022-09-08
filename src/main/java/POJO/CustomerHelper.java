package POJO;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;
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
                .customer_profile_status(faker.random().nextBoolean())
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
            ps.setBoolean(6, customer.isCustomer_profile_status());
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

    //To be implemented in next story
    //    public Customer getByID(int id) {
//        ResultSet rs = customersDbConnection.getCustomerById(id);
//        Customer customer = null;
//
//        ResultSetMetaData rsmd = null;
//        try {
//            rsmd = rs.getMetaData();
//
//            int columnsNumber = rsmd.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = rs.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            while (rs.next()) {
//                customer = Customer.builder().
//                        customerId(rs.getString("customer_id"))
//                        .name(rs.getString("name"))
//                        .email(rs.getString("email"))
//                        .phone(rs.getString("phone"))
//                        .age(rs.getInt("age"))
//                        .gdpr(rs.getBoolean("gdpr"))
//                        .customer_profile_status(rs.getBoolean("customer_profile_status"))
//                        .deactivation_date(rs.getDate("deactivation_date"))
//                        .reason(rs.getString("reason"))
//                        .notes(rs.getString("notes"))
//                        .activation_date(rs.getDate("activation_date"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return customer;
//    }
//
//
////    @Override
////    public List<Customer> getByIDs(int j) {
////
////        List<Customer> customersList;
////        ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();
////        ResultSet rs = customersDbConnection.getCustomerById(2);
////        ResultSetMetaData rsmd = null;
////        try {
////            rsmd = rs.getMetaData();
////
////            int columnsNumber = rsmd.getColumnCount();
////            while (rs.next()) {
////                for (int i = 1; i <= columnsNumber; i++) {
////                    if (i > 1) System.out.print(",  ");
////                    String columnValue = rs.getString(i);
////                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
////                }
////                System.out.println("");
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////        List<Customer> pojoList = resultSetMapper.mapResultSetToObject(rs, Customer.class);
////        System.out.println(pojoList.toString());
////        return pojoList;
////    }
//

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

}

