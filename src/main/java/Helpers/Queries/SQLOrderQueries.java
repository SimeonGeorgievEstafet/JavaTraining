package Helpers.Queries;

public interface SQLOrderQueries {

    String SAVE_ORDER = "insert into orders ( customer_id, is_order_completed, is_order_paid, date_of_order ) values (%s,now())returning *;";

    String GET_ORDER_BY_ID = "select * from orders where id in (?)";

    String DELETE_ORDER = "delete from orders where id = ? returning *;";

    String GET_ALL_CUSTOMER_ORDERS = "select * from orders where id in (?)";

}
