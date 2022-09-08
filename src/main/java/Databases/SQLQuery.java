package Databases;

import POJO.Customer;

public class SQLQuery {

    public static String getAllResultsFromTable(String table) {
        return "SELECT * FROM " + table;
    }

    public static String getCustomerById(int Id) {
        return "SELECT * FROM customers_1 WHERE customer_id =" + Id;
    }

    public static String saveCustomer(Customer customer) {
        return "insert\n" +
                "\tinto\n" +
                "\tcustomers_1 (\n" +
                "\tname,\n" +
                "\temail,\n" +
                "\tphone,\n" +
                "\tage,\n" +
                "\tgdpr,\n" +
                "\tcustomer_profile_status,\n" +
                "\tdeactivation_date,\n" +
                "\treason,\n" +
                "\tnotes)\n" +
                "values ('" + customer.getName() + "',\n" +
                "'" + customer.getEmail() + "',\n" +
                "'" + customer.getPhone() + "',\n" +
                "" + customer.getAge() + ",\n" +
                "" + customer.isGdpr() + ",\n" +
                "" + customer.isCustomer_profile_status() + ",\n" +
                "current_timestamp,\n" +
                "'" + customer.getReason() + "',\n" +
                "'" + customer.getNotes() + "')" +
                "RETURNING *;\n";
    }

    public static String deactivateCustomer(int customerId) {
        return "update\n" +
                "\tcustomers_1\n" +
                "set\n" +
                "\tdeactivation_date = CURRENT_TIMESTAMP,\n" +
                "\tcustomer_profile_status = false,\n" +
                "\treason = 'reason for deactivation',\n" +
                "\tnotes = 'some long note here'\n" +
                "where\n" +
                "\tcustomer_id = " + customerId + "\n" +
                "returning *;";
    }

    public static String activateCustomer(int customerId) {
        return "update\n" +
                "\tcustomers_1\n" +
                "set\n" +
                "\tdeactivation_date = null, \n" +
                "\tactivation_date = NOW(), \n" +
                "\tcustomer_profile_status = true,\n" +
                "\tnotes = 'some note for activating the customer',\n" +
                "\treason = null\n" +
                "where\n" +
                "\tcustomer_id =" + customerId + "\n" +
                "returning *;";
    }

    public static String deleteCustomer(int customerId) {
        return "DELETE FROM customers_1 \n" +
                "WHERE customer_id = " + customerId + "\n" +
                "returning *;";
    }

    public static String getRandomCustomer() {
        return "select\n" +
                "\tcustomer_id\n" +
                "from\n" +
                "\tcustomers_1\n" +
                "order by\n" +
                "\trandom()\n" +
                "limit 1";
    }
    public static String deleteAll() {
        return "TRUNCATE TABLE customers_1";
    }

    public static String getRandomCustomers(int i) {
        return "select\n" +
                "\tcustomer_id\n" +
                "from\n" +
                "\tcustomers_1\n" +
                "order by\n" +
                "\trandom()\n" +
                "limit " + i + "\n" +
                "returning *;";
    }

    public static String getRecordsCount() {
        return "SELECT \n" +
                "   COUNT(*) \n" +
                "FROM \n" +
                "   customers_1";
    }

}
