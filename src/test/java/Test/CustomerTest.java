package Test;

import Dao.CustomerDao;
import Helpers.CustomerHelper;
import POJO.Customer;

import java.util.List;

public class CustomerTest {
    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDao();
        Customer customer = new CustomerHelper().CreateObject();
        customerDao.save(customer);
        customerDao.deleteAll();
        customerDao.update("deactivate", customerDao.getRandomId());
        customerDao.update("activate", customerDao.getRandomId());
        customerDao.delete(customerDao.getRandomId());
        customerDao.deleteAll();

        //New
        customerDao.getRecordsCount();
        customerDao.getRandomId();
        customerDao.getRandomIds(5);
        customerDao.getByID(customerDao.getRandomId());
        customerDao.getByIDs(customerDao.getRandomIds(5));
    }
}
