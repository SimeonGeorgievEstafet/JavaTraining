package Helpers.Queries;

public interface SQLProductQueries {

    String SAVE_PRODUCT = "insert into products_inventory ( product_name, available_quantity, product_type, price_without_vat, price_with_vat, in_stock, supplier) values (%s)returning *;";

    String GET_PRODUCT_BY_ID = "select * from products_inventory where id in (?)";

    String DELETE_PRODUCT = "delete from products_inventory where id = ? returning *;";

}
