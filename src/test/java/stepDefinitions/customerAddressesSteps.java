package stepDefinitions;

import Dao.CustomerAddressDao;
import Dao.CustomerDao;
import Helpers.CustomerAddressHelper;
import Helpers.CustomerHelper;
import POJO.Customer;
import POJO.CustomerAddress;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class customerAddressesSteps {
    CustomerAddressDao customerAddressDao = new CustomerAddressDao();
    CustomerDao customerDao = new CustomerDao();
    int numberOfCustomerAddresses;

    @Given("clear the data in table customerAddresses")
    public void clearTheDataInTableCustomerAddresses() {
        customerAddressDao.truncate();
    }

    @Then("create {int} new customerAddresses")
    public void createNewCustomerAddresses(int numberOfCustomerAddresses) {
        this.numberOfCustomerAddresses = numberOfCustomerAddresses;
        for (int i = 0; i < this.numberOfCustomerAddresses; i++) {
            customerAddressDao.save(new CustomerAddressHelper().CreateObject());
            customerDao.save(new CustomerHelper().CreateObject());
        }
    }

    @And("verify customerAddresses are created")
    public void verifyCustomerAddressesAreCreated() {
        Assertions.assertEquals(customerAddressDao.getRecordsCount(), numberOfCustomerAddresses);
    }

    @Then("verify addresses of {int} random customers.")
    public void verifyAddressesOfRandomCustomers(int count) {
        List<Customer> customers = customerDao.getByIDs(customerDao.getRandomIds(count));
        for (Customer customer : customers) {
            Assertions.assertNotNull(customer.getCustomerAddress());
        }
    }

    @Then("get {int} addresses and verify mandatory fields")
    public void getAddressesAndVerifyMandatoryFields(int numberOfCustomerAddresses) {
        List<CustomerAddress> addressList = new ArrayList<>();
        addressList = customerAddressDao.getByIDs(customerAddressDao.getRandomIds(numberOfCustomerAddresses));
        for (CustomerAddress customerAddress : addressList) {
            Assertions.assertNotNull(customerAddress.getAddress());
            Assertions.assertNotNull(customerAddress.getCity());
            Assertions.assertNotNull(customerAddress.getProvince());
            Assertions.assertNotNull(customerAddress.getPostalCode());
            Assertions.assertNotNull(customerAddress.getCountry());
        }
    }
}
