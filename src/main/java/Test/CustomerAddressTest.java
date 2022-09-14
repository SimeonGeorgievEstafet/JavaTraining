package Test;

import Dao.CustomerAddressDao;
import Helpers.CustomerAddressHelper;
import POJO.CustomerAddress;

import java.util.List;

public class CustomerAddressTest {
    public static void main(String[] args) {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddress customerAddress = new CustomerAddressHelper().CreateCustomerAddress();

        customerAddressDao.update(new CustomerAddressHelper().CreateCustomerAddress(), 84);
        customerAddressDao.delete(84);

        customerAddressDao.save(customerAddress);
        customerAddressDao.getByID(6);

//        Create 15 customer addresses!
        for (int i = 0; i < 100; i++) {
            CustomerAddress customerAddress2 = new CustomerAddressHelper().CreateCustomerAddress();
            customerAddressDao.save(customerAddress2);
        }
        List<CustomerAddress> customerAddressList = new CustomerAddressHelper().CreateCustomerAddresses(2);
    }
}
