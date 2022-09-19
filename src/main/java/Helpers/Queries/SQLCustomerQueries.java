package Helpers.Queries;

public interface SQLCustomerQueries {

    String GET_RANDOM_CUSTOMER = "select id from customers order by random() limit 1";

    String SAVE_CUSTOMER = "insert into customers ( name, email, phone, age, gdpr, customer_profile_status, reason, notes) values (%s) RETURNING *;";

    String DEACTIVATE_CUSTOMER = "update customers set deactivation_date = current_timestamp, customer_profile_status = false, reason = 'reason for deactivation', notes = 'some long note here' where id = %s returning *;";

    String ACTIVATE_CUSTOMER = "update customers set deactivation_date = null, activation_date = now(), customer_profile_status = true, notes = 'some note for activating the customer', reason = null where id = %s returning *;";

    String DELETE_CUSTOMER = "delete from customers where id = %s returning *;";

    String GET_RANDOM_IDS = "select array( select id from customers order by random() limit + ? )";

    String GET_CUSTOMER_BY_ID = "select * from customers where id = %s";
    
    String GET_CUSTOMER_BY_IDS = "select * from customers where id in (?)";

    String SAVE_CUSTOMER_ADDRESS = "insert into customer_addresses ( address, city, province, state, postal_code, country) values (?,?,?,?,?,?)returning *;";

    String GET_CUSTOMER_ADDRESS_BY_ID = "select * from customer_addresses where id in (?)";

    String DELETE_CUSTOMER_ADDRESS = "delete from customer_addresses where id = ? returning *;";

    String UPDATE_CUSTOMER_ADDRESS = "update customer_addresses set address = ?, city = ?, province = ?, state = ?, postal_code = ?, country = ? where id = ? returning *;";

}
