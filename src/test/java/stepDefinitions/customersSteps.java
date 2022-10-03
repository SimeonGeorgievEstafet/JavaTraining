package stepDefinitions;

import Dao.CustomerAddressDao;
import Dao.CustomerDao;
import Helpers.CustomerAddressHelper;
import Helpers.CustomerHelper;
import POJO.Customer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class customersSteps {

    CustomerDao customerDao = new CustomerDao();
    CustomerAddressDao customerAddressDao = new CustomerAddressDao();

    Customer createdCustomer;
    Customer customerFromDatabase;
    int numberOfCustomers;
    List<Customer> ids;


    @Given("clear the data in table customers")
    public void truncateDatabase() {
        customerDao.truncate();
    }

    @Given("create {int} new customers")
    public void createCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
        for (int i = 0; i < this.numberOfCustomers; i++) {
            customerDao.save(new CustomerHelper().CreateObject());
        }
    }


    @And("verify customers are created")
    public void verifyCustomersAreCreated() {
        Assertions.assertEquals(customerDao.getRecordsCount(), numberOfCustomers);
    }

    @Given("new customer is created")
    public void newCustomerIsCreated() {
        createdCustomer = new CustomerHelper().CreateObject();
        if (customerAddressDao.getRecordsCount() > customerDao.getRecordsCount()) {
            customerDao.save(createdCustomer);
        } else {
            customerAddressDao.save(new CustomerAddressHelper().CreateObject());
            customerDao.save(createdCustomer);
        }
    }

    @Then("verify customer is saved in Database")
    public void verifyCustomerIsCreated() {
        customerFromDatabase = customerDao.getByID(numberOfCustomers + 1);
        Assertions.assertEquals(customerFromDatabase, createdCustomer);
        Assertions.assertNotEquals(numberOfCustomers, customerDao.getRecordsCount());
    }


    @When("delete random customer from the Database")
    public void deleteRandomCustomerFromTheDatabase() {
        numberOfCustomers = customerDao.getRecordsCount();
        customerDao.delete(customerDao.getRandomId());
    }

    @Then("verify customer is deleted from Database")
    public void verifyCustomerIsDeletedFromDatabase() {
        Assertions.assertNotEquals(numberOfCustomers, customerDao.getRecordsCount());
    }

    @Given("users are provided for data verification:")
    public void usersAreProvidedForDataVerification(DataTable table) {
        List <Integer> ids = new ArrayList<>();
        for (int i = 0; i < table.height(); i++) {
            ids.add(Integer.valueOf(table.cell(i, 0)));
        }
        this.ids = customerDao.getByIDs(ids);
    }

    @Then("verify all mandatory fields for users are saved in database.")
    public void verifyAllMandatoryFieldsForUsersAreSavedInDatabase() {
        for (Customer id : ids) {
            Assertions.assertNotNull(id.getName());
            Assertions.assertNotNull(id.getEmail());
            Assertions.assertNotNull(id.getPhone());
        }
    }

    @Then("verify {int} customers are saved.")
    public void verifyCustomersAreSaved(int customersCount) {
        Assertions.assertEquals(customersCount,numberOfCustomers);
    }
}
