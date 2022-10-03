package Test;

import Dao.CustomerAddressDao;
import Helpers.CustomerAddressHelper;
import POJO.CustomerAddress;

public class CustomerAddressTest {
    public static void main(String[] args) {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddress customerAddress = new CustomerAddressHelper().CreateObject();
        customerAddressDao.truncate();
        customerAddressDao.save(customerAddress);
//        customerAddressDao.update(new CustomerAddressHelper().CreateObject(), customerAddressDao.getRandomId());
//        customerAddressDao.delete(customerAddressDao.getRandomId());
//        customerAddressDao.deleteAll();
//        customerAddressDao.getRecordsCount();
//        customerAddressDao.getRandomId();
//        customerAddressDao.getRandomIds(5);
//
//        //New
//        customerAddressDao.getByID(customerAddressDao.getRandomId());
//        customerAddressDao.getByIDs(customerAddressDao.getRandomIds(4));

    }
}
