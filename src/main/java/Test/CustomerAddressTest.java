package Test;

import Helpers.CustomerAddressHelper;
import POJO.CustomerAddress;

public class CustomerAddressTest {
    public static void main(String[] args) {
        CustomerAddressHelper CAH = new CustomerAddressHelper();
        CustomerAddress customerAddress = CAH.CreateCustomerAddress();

        CAH.update(CAH.CreateCustomerAddress(), 84);
        CAH.delete(84);

        CAH.save(customerAddress);
        CAH.getByID(6);

//        Create 15 customer addresses!
        for (int i = 0; i < 10; i++) {
            CustomerAddress customerAddress2 = CAH.CreateCustomerAddress();
            CAH.save(customerAddress2);
        }
    }
}
