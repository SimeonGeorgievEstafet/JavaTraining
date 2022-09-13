package Handlers;

import POJO.Customer;
import POJO.Product;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductHandler extends BeanListHandler<Product> {
    public ProductHandler() {
        super(Product.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public List<Product> handle(ResultSet rs) throws SQLException {
        return super.handle(rs);
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("product_id", "productId");
        columnsToFieldsMap.put("product_name", "productName");
        columnsToFieldsMap.put("available_quantity", "availableQuantity");
        columnsToFieldsMap.put("product_type", "productType");
        columnsToFieldsMap.put("price_without_vat", "priceWithoutVat");
        columnsToFieldsMap.put("price_with_vat", "priceWithVat");
        columnsToFieldsMap.put("in_stock", "inStock");
        return columnsToFieldsMap;
    }
}
