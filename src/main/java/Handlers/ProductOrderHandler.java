package Handlers;

import POJO.ProductOrder;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOrderHandler extends BeanListHandler<ProductOrder> {


    public ProductOrderHandler() {
        super(ProductOrder.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("product_id", "productId");
        columnsToFieldsMap.put("order_id", "orderId");
        columnsToFieldsMap.put("ordered_quantity", "orderedQuantity");
        return columnsToFieldsMap;
    }

    public List<ProductOrder> handle(ResultSet rs) throws SQLException {
        return super.handle(rs);
    }
}
