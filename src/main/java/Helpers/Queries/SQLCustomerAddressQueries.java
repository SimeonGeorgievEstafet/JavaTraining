package Helpers.Queries;

public interface SQLCustomerAddressQueries {


    String SAVE_CUSTOMER_ADDRESS = "insert into customer_addresses ( address, city, province, state, postal_code, country) values (%s)returning *;";

    String GET_CUSTOMER_ADDRESS_BY_ID = "select * from customer_addresses where id in (?)";

    String DELETE_CUSTOMER_ADDRESS = "delete from customer_addresses where id = ? returning *;";

}
