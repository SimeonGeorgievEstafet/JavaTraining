Feature: Tests that validate the data in the customer's table

  Background:
    Given clear the data in table customers
    When create 10 new customers
    And verify customers are created

  Scenario: Create and validate the data in the customers table
    When new customer is created
    Then verify customer is saved in Database

  Scenario: Delete a customer from Database
    When delete random customer from the Database
    Then verify customer is deleted from Database

  Scenario: Get many customers and verify mandatory data is saved.
    Given users are provided for data verification:
      | 1  |
      | 3  |
      | 5  |
      | 9  |
    Then verify all mandatory fields for users are saved in database.

    Scenario: Verify data count in customers table
      Then verify 10 customers are saved.
