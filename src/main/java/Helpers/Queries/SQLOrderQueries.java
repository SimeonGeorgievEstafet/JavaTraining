package Helpers.Queries;

public interface SQLOrderQueries {

    String SAVE_ORDER = "insert into orders ( customer_id, is_order_completed, is_order_paid, date_of_order ) values ((select id from customers order by random() limit 1),%s,now()) RETURNING *";
}
