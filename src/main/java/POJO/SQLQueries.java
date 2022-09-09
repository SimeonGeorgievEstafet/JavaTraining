package POJO;

public interface SQLQueries {

    String GET_RANDOM_CUSTOMER = "select\n" +
            "\tcustomer_id\n" +
            "from\n" +
            "\tcustomers_1\n" +
            "order by\n" +
            "\trandom()\n" +
            "limit 1";


    String SAVE_CUSTOMER = "insert\n" +
            "\tinto\n" +
            "\tcustomers_1 (\n" +
            "\tname,\n" +
            "\temail,\n" +
            "\tphone,\n" +
            "\tage,\n" +
            "\tgdpr,\n" +
            "\tcustomer_profile_status,\n" +
            "\treason,\n" +
            "\tnotes)\n" +
            "values (?,?,?,?,?,?,?,?)RETURNING *;";

    String DEACTIVATE_CUSTOMER = "update\n" +
            "\tcustomers_1\n" +
            "set\n" +
            "\tdeactivation_date = CURRENT_TIMESTAMP,\n" +
            "\tcustomer_profile_status = false,\n" +
            "\treason = 'reason for deactivation',\n" +
            "\tnotes = 'some long note here'\n" +
            "where\n" +
            "customer_id = ?" +
            "returning *;";

    String ACTIVATE_CUSTOMER = "update\n" +
            "\tcustomers_1\n" +
            "set\n" +
            "\tdeactivation_date = null, \n" +
            "\tactivation_date = NOW(), \n" +
            "\tcustomer_profile_status = true,\n" +
            "\tnotes = 'some note for activating the customer',\n" +
            "\treason = null\n" +
            "where\n" +
            "\tcustomer_id = ?" +
            "returning *;";

    String DELETE_USER = "DELETE FROM customers_1 \n" +
            "WHERE customer_id = ?\n" +
            "returning *;";

    String DELETE_ALL_USERS = "TRUNCATE TABLE customers_1";

    String GET_RANDOM_IDS = "select\n" +
            "\tarray(\n" +
            "\tselect\n" +
            "\t\tcustomer_id\n" +
            "\tfrom\n" +
            "\t\tcustomers_1\n" +
            "\torder by\n" +
            "\t\trandom()\n" +
            "\tlimit + ? \n" +
            ")";

    String GET_RECORD_COUNT = "SELECT \n" +
            "   COUNT(*) \n" +
            "FROM \n" +
            "   customers_1";

    String GET_CUSTOMER_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tcustomers_1\n" +
            "where\n" +
            "\tcustomer_id = ?";


    String GET_CUSTOMER_BY_IDs = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tcustomers_1\n" +
            "where\n" +
            "\tcustomer_id in (?)";

}
