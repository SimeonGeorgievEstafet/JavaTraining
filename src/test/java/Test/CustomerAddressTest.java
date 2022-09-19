package Test;

import Dao.CustomerAddressDao;
import Helpers.CustomerAddressHelper;
import POJO.CustomerAddress;

import java.util.List;

public class CustomerAddressTest {
    public static void main(String[] args) {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddress customerAddress = new CustomerAddressHelper().CreateObject();
        customerAddressDao.save(customerAddress);
        customerAddressDao.update(new CustomerAddressHelper().CreateObject(), customerAddressDao.getRandomId());
        customerAddressDao.delete(customerAddressDao.getRandomId());
        customerAddressDao.deleteAll();

        //NEW
        customerAddressDao.getRecordsCount();
        customerAddressDao.getRandomId();
        customerAddressDao.getRandomIds(5);
        customerAddressDao.getByID(customerAddressDao.getRandomId());
        customerAddressDao.getByIDs(customerAddressDao.getRandomIds(4));

    }
}
