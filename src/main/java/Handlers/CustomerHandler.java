package Handlers;

import POJO.Customer;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerHandler extends BeanListHandler<Customer> {


    public CustomerHandler() {
        super(Customer.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("customer_id", "customerId");
        columnsToFieldsMap.put("activation_date", "activationDate");
        columnsToFieldsMap.put("deactivation_date", "deactivationDate");
        return columnsToFieldsMap;
    }

    public List<Customer> handle(ResultSet rs) throws SQLException {
        return super.handle(rs);
    }
}
