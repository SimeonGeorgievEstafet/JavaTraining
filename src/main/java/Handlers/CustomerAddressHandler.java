package Handlers;

import POJO.CustomerAddress;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerAddressHandler extends BeanListHandler<CustomerAddress> {
    public CustomerAddressHandler() {
        super(CustomerAddress.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("postal_code", "postalCode");
        return columnsToFieldsMap;
    }

    public List<CustomerAddress> handle(ResultSet rs) throws SQLException {
        return super.handle(rs);
    }
}
