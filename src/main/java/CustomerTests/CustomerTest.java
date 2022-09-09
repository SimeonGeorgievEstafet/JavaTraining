package CustomerTests;

public class CustomerTest {
    public static void main(String[] args) {
        final String ACTIVATE = "activate";
        final String DEACTIVATE = "deactivate";
        CustomerHelper CDI = new CustomerHelper();
        Customer customer = CDI.CreateCustomer();

//        Get random customerId
        CDI.getRandomId();

//        save new customer
        CDI.save(customer);

//        delete all customers
        CDI.deleteAll();

//        Create 15 customers!
        for (int i = 0; i < 15; i++) {
            Customer customer2 = CDI.CreateCustomer();
            CDI.save(customer2);
        }

//        Deactivate customer
        CDI.update(DEACTIVATE, 178);

//        Activate customer
        CDI.update(ACTIVATE, 178);

//        Delete customer by ID
        CDI.delete(CDI.getRandomId());

//        Get many random customerIDs
        CDI.getRandomIds(5);

//        Get total count of customers
        CDI.getRecordsCount();

//        Get customer by id with manual mapping of the fields
        CDI.getByID(CDI.getRandomId());

//        Get many customers by list of ids with manual mapping of the fields
        CDI.getByIDs(CDI.getRandomIds(5));

//        Get customer by id with ResultSetMapper
        CDI.getByIdResultSetMapper(CDI.getRandomIds(3));

//        Get many customers by list of ids with ResultSetMapper
        CDI.getByIdResultSetMapper(CDI.getRandomId());

//        Get customer by id with Reflection
        CDI.getByIdReflection(CDI.getRandomId());

    }
}
