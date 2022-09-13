package Test;

import Helpers.CustomerAddressHelper;
import Helpers.CustomerHelper;
import Helpers.ProductsHelper;
import POJO.Customer;
import POJO.Product;

public class CustomerTest {
    public static void main(String[] args) {
        final String ACTIVATE = "activate";
        final String DEACTIVATE = "deactivate";
        CustomerHelper CH = new CustomerHelper();
        Customer customer = CH.CreateCustomer();

        CH.getCustomerAddress(13);
        CH.getCustomerOrders(2);

//        Get random customerId
        CH.getRandomId();

//        save new customer
        CH.save(customer);

//        delete all customers
        CH.deleteAll();

//        Create 15 customers!
        for (int i = 0; i < 100; i++) {
            Customer customer2 = CH.CreateCustomer();
            CH.save(customer2);
        }


//        Deactivate customer
        CH.update(DEACTIVATE, 6);

//        Activate customer
        CH.update(ACTIVATE, 6);

//        Delete customer by ID
        CH.delete(1001);

//        Get many random customerIDs
        CH.getRandomIds(5);

//        Get total count of customers
        CH.getRecordsCount();

//        Get customer by id with manual mapping of the fields
        CH.getByID(CH.getRandomId());

//        Get many customers by list of ids with manual mapping of the fields
        CH.getByIDs(CH.getRandomIds(5));

//        Get customer by id with ResultSetMapper
        CH.getByIdResultSetMapper(CH.getRandomIds(3));

//        Get many customers by list of ids with ResultSetMapper
        CH.getByIdResultSetMapper(CH.getRandomId());

//        Get customer by id with Reflection method
        CH.getByIdReflection(CH.getRandomId());


//        Get customer by id with DB Utils method
        CH.getByIdDbUtils(CH.getRandomId());
//
////       Get customer list by ids with DB Utils method
//        CH.getByIdsDbUtils(CH.getRandomIds(5));
    }
}
