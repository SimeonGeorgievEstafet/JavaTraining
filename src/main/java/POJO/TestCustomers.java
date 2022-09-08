package POJO;

public class TestCustomers {
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

        //To be implemented in next story

//        CDI.getByID(CDI.getRandomId());
//        CDI.getByIDs(2);
    }
}
