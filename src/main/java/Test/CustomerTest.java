package Test;

import Dao.CustomerDao;
import Helpers.CustomerHelper;
import POJO.Customer;

public class CustomerTest {
    public static void main(String[] args) {

//        final String ACTIVATE = "activate";
//        final String DEACTIVATE = "deactivate";
//        Customer customer = new CustomerHelper().CreateObject();
        CustomerDao customerDao = new CustomerDao();
//
//        List<Customer> customerList = new CustomerHelper().CreateObjects(6);
//        customerDao.getCustomerAddress(13);
//        customerDao.getCustomerOrders(2);
//
////        Get random customerId
//        customerDao.getRandomId();
//
////        save new customer
//        customerDao.save(customer);
//
//////        delete all customers
        customerDao.deleteAll("customers_1");
//
////        Create 15 customers!
//        for (int i = 0; i < 100; i++) {
//            Customer customer2 = new CustomerHelper().CreateObject();
//            customerDao.save(customer2);
//        }
//
//
////        Deactivate customer
//        customerDao.update(DEACTIVATE, 6);
//
////        Activate customer
//        customerDao.update(ACTIVATE, 6);
//
////        Delete customer by ID
//        customerDao.delete(1222);
//        customerDao.deleteAll();
//
////        Get many random customerIDs
//        customerDao.getRandomIds(5);
//
////        Get total count of customers
//        customerDao.getRecordsCount();
//
////        Get customer by id with manual mapping of the fields
//        customerDao.getByID(customerDao.getRandomId());
//
////        Get many customers by list of ids with manual mapping of the fields
//        customerDao.getByIDs(customerDao.getRandomIds(5));
//
////        Get customer by id with ResultSetMapper
//        customerDao.getByIdResultSetMapper(customerDao.getRandomIds(3));
//
////        Get many customers by list of ids with ResultSetMapper
//        customerDao.getByIdResultSetMapper(customerDao.getRandomId());
//
////        Get customer by id with Reflection method
//        customerDao.getByIdReflection(customerDao.getRandomId());
//
//
////        Get customer by id with DB Utils method
//        customerDao.getByIdDbUtils(customerDao.getRandomId());
////
//////       Get customer list by ids with DB Utils method
////        customerDao.getByIdsDbUtils(customerDao.getRandomIds(5));
    }
}
