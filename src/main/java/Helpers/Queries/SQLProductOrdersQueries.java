package Helpers.Queries;

public interface SQLProductOrdersQueries {

    String SAVE_PRODUCT_ORDER = "insert into product_orders (order_id, product_id, ordered_quantity) values ((select id from orders order by random() limit 1),(select id from products_inventory order by random() limit 1),%s) returning *";

    String GET_PRODUCT_ORDER_BY_ID = "select * from product_orders where id in (?)";

    String DELETE_PRODUCT_ORDER = "delete from product_orders where id = ? returning *;";

    String UPDATE_PRODUCT_ORDER = "update product_orders set ordered_quantity = ? where id = ? returning *;";

    String GET_PRODUCT_ORDER_BY_ORDER_ID = "select * from product_orders where order_id in (?)";
}
