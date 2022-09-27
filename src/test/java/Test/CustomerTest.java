package Test;

import Dao.CustomerDao;
import Helpers.CustomerHelper;
import POJO.Customer;

public class CustomerTest {
    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDao();
//        Customer customer = new CustomerHelper().CreateObject();
//        customerDao.save(customer);
//        customerDao.deleteAll();
//        customerDao.update("deactivate", customerDao.getRandomId());
//        customerDao.update("activate", customerDao.getRandomId());
//        customerDao.delete(customerDao.getRandomId());
//        customerDao.deleteAll();
//        customerDao.getRecordsCount();
//        customerDao.getRandomId();
//        customerDao.getRandomIds(5);

        //New
        customerDao.getByID(customerDao.getRandomId());
        customerDao.getByIDs(customerDao.getRandomIds(5));
    }
}
