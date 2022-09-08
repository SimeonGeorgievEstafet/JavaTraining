package POJO;

import Databases.DatabaseSingleton.DatabaseSingletonHelper;
import Databases.SQLQueries;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    List<Customer> customers;

    public CustomerDaoImpl() throws SQLException {
    }

    public void StudentDaoImpl() {
        customers = new ArrayList<Customer>();
    }

    public Customer CreateCustomer() {

        Faker faker = new Faker();
        return Customer.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .age(faker.random().nextInt(18, 99))
                .gdpr(faker.random().nextBoolean())
                .customer_profile_status(faker.random().nextBoolean())
                .reason(faker.lorem().fixedString(10))
                .notes(faker.lorem().fixedString(10))
                .build();
    }

//    @Override
//    public List<Customer> getAllCustomers() throws SQLException {
//        ResultSet rs = customersDbConnection.getAllCustomers();
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
//        return customers;
//    }

    //    @Override
//    public void save(Customer customer) throws JsonProcessingException {
//        customersDbConnection.saveCustomer(customer);
//    }
//
//    @Override
//    public void update(String status, int customerId) {
//        ResultSet rs = customersDbConnection.updateCustomer(status, customerId);
//        ResultSetMetaData rsmd = null;
//        try {
//            rsmd = rs.getMetaData();
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
//    }
//
//    @Override
//    public void delete(int customerId) {
//        ResultSet rs = customersDbConnection.deleteCustomer(customerId);
//        ResultSetMetaData rsmd = null;
//        try {
//            rsmd = rs.getMetaData();
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
//    }
//
//

    /**
     * This method will get random customer from the DB and will return customerId
     *
     * @return
     */
    @Override
    public int getRandomCustomer() {
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
//    @Override
//    public int getRecordsCount() {
//        ResultSet rs = customersDbConnection.getRecordsCount();
//        int CustomerId = 0;
//        try {
//            if (rs.next()) {
//                CustomerId = Integer.parseInt(rs.getString(1));
//                System.out.println(CustomerId);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return CustomerId;
//    }
//
//    @Override
//    public ArrayList<Integer> getRandomIds(int j) {
//        ArrayList<Integer> customerIds = new ArrayList<>();
//        for (int i = 0; i < j; i++) {
////            customerIds.add(getRandomCustomer());
//        }
//        System.out.println(customerIds);
//        return customerIds;
//    }
//
//    @Override
//    public void deleteAll() {
//        customersDbConnection.deleteAll();
//    }

}

