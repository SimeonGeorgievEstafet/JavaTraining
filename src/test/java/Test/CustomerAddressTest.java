package Test;

import Dao.CustomerAddressDao;
import Helpers.CustomerAddressHelper;
import POJO.CustomerAddress;

import java.util.List;

public class CustomerAddressTest {
    public static void main(String[] args) {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
//        customerAddressDao.getRecordsCount();
        CustomerAddress customerAddress = new CustomerAddressHelper().CreateObject();
//
//        customerAddressDao.update(new CustomerAddressHelper().CreateObject(), 86);
//        customerAddressDao.delete(84);
            customerAddressDao.deleteAll();
//        customerAddressDao.save(customerAddress);
//        customerAddressDao.getByID(6);
//
////        Create 15 customer addresses!
//        for (int i = 0; i < 100; i++) {
//            CustomerAddress customerAddress2 = new CustomerAddressHelper().CreateObject();
//            customerAddressDao.save(customerAddress2);
//        }
//        List<CustomerAddress> customerAddressList = new CustomerAddressHelper().CreateObjects(2);
    }
}
