package Handlers;

import POJO.Order;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHandler extends BeanListHandler<Order> {

    public OrderHandler() {
        super(Order.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("order_id", "orderId");
        columnsToFieldsMap.put("customer_id", "customerId");
        columnsToFieldsMap.put("is_order_completed", "isOrderCompleted");
        columnsToFieldsMap.put("is_order_paid", "isOrderPaid");
        columnsToFieldsMap.put("date_of_order", "dateOfOrder");
        columnsToFieldsMap.put("date_of_order_completed", "dateOfOrderCompleted");
        return columnsToFieldsMap;
    }

    public List<Order> handle(ResultSet rs) throws SQLException {
        return super.handle(rs);
    }
}
