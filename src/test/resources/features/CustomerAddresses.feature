@CustomerAddresses
Feature: Tests that validate the data in the customerAddresses table

  Background:
    Given clear the data in table customersAddresses
    Then create 10 new customerAddresses
    And verify customerAddresses are created

  @Smoke
  Scenario: Get many customers and verify mandatory data is saved.
    Then verify addresses of 10 random customers.

  Scenario: Get many customerAddresses and verify mandatory data is saved.
    Then get 5 addresses and verify mandatory fields
