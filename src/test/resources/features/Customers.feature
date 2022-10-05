@Customers
Feature: Tests that validate the data in the customer's table

  Background:
    Given clear the data in table customers
    When create 10 new customers
    And There are equal customers in Database

  @Smoke
  Scenario: Create and validate the data in the customers table
    When new customer is created
    Then verify customer is saved in Database

  Scenario: Delete a customer from Database
    When get customer count from Database
    When delete random customer from the Database
    And There are less customers in Database

  Scenario: Get many customers and verify mandatory data is saved.
    Given 5 users are provided for data verification
    Then verify all mandatory fields for users are saved in database.

  Scenario: Verify data count in customers table
    And There are equal customers in Database
