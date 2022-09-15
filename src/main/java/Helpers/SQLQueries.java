package Helpers;

public interface SQLQueries {

    String GET_RANDOM_CUSTOMER = "select\n" +
            "\tid\n" +
            "from\n" +
            "\tcustomers\n" +
            "order by\n" +
            "\trandom()\n" +
            "limit 1";

    String SAVE_CUSTOMER = "insert\n" +
            "\tinto\n" +
            "\tcustomers (\n" +
            "\tname,\n" +
            "\temail,\n" +
            "\tphone,\n" +
            "\tage,\n" +
            "\tgdpr,\n" +
            "\tcustomer_profile_status,\n" +
            "\treason,\n" +
            "\tnotes)\n" +
            "values (%s) RETURNING *;";

    String DEACTIVATE_CUSTOMER = "update\n" +
            "\tcustomers\n" +
            "set\n" +
            "\tdeactivation_date = CURRENT_TIMESTAMP,\n" +
            "\tcustomer_profile_status = false,\n" +
            "\treason = 'reason for deactivation',\n" +
            "\tnotes = 'some long note here'\n" +
            "where\n" +
            "id = ?" +
            "returning *;";

    String ACTIVATE_CUSTOMER = "update\n" +
            "\tcustomers\n" +
            "set\n" +
            "\tdeactivation_date = null, \n" +
            "\tactivation_date = NOW(), \n" +
            "\tcustomer_profile_status = true,\n" +
            "\tnotes = 'some note for activating the customer',\n" +
            "\treason = null\n" +
            "where\n" +
            "\tid = ?" +
            "returning *;";

    String DELETE_CUSTOMER = "DELETE FROM customers \n" +
            "WHERE id = %s\n" +
            "returning *;";

    String DELETE_ALL_CUSTOMERS = "DELETE FROM %s WHERE id is not null";
            ;

    String GET_RANDOM_IDS = "select\n" +
            "\tarray(\n" +
            "\tselect\n" +
            "\t\tid\n" +
            "\tfrom\n" +
            "\t\tcustomers\n" +
            "\torder by\n" +
            "\t\trandom()\n" +
            "\tlimit + ? \n" +
            ")";

    String GET_RECORD_COUNT = "SELECT \n" +
            "   COUNT(*) \n" +
            "FROM \n" +
            "%s" ;

    String GET_CUSTOMER_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tcustomers\n" +
            "where\n" +
            "\tid = %s";


    String GET_CUSTOMER_BY_IDS = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tcustomers\n" +
            "where\n" +
            "\tid in (?)";

    String SAVE_CUSTOMER_ADDRESS = "insert\n" +
            "\tinto\n" +
            "\tcustomer_addresses (\n" +
            "\taddress ,\n" +
            "\tcity ,\n" +
            "\tprovince ,\n" +
            "\tstate ,\n" +
            "\tpostal_code ,\n" +
            "\tcountry)\n" +
            "values (?,?,?,?,?,?)RETURNING *;";

    String GET_CUSTOMER_ADDRESS_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tcustomer_addresses\n" +
            "where\n" +
            "\tid in (?)";

    String DELETE_CUSTOMER_ADDRESS = "DELETE FROM customer_addresses \n" +
            "WHERE id = ?\n" +
            "returning *;";

    String UPDATE_CUSTOMER_ADDRESS = "update\n" +
            "\tcustomer_addresses \n" +
            "set\n" +
            "\taddress  = ?, \n" +
            "\tcity  = ?, \n" +
            "\tprovince = ?,\n" +
            "\tstate  = ?,\n" +
            "\tpostal_code  = ?,\n" +
            "\tcountry  = ?\n" +
            "where\n" +
            "\tid = ?\n" +
            "returning *;";

    String SAVE_PRODUCT = "insert\n" +
            "\tinto\n" +
            "\tproducts_inventory (\n" +
            "\tproduct_name ,\n" +
            "\tavailable_quantity ,\n" +
            "\tproduct_type ,\n" +
            "\tprice_without_vat ,\n" +
            "\tprice_with_vat ,\n" +
            "\tin_stock ,\n" +
            "\tsupplier)\n" +
            "values (?,?,?,?,?,?,?)RETURNING *;";

    String GET_PRODUCT_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tproducts_inventory\n" +
            "where\n" +
            "\tid in (?)";

    String DELETE_PRODUCT = "DELETE FROM products_inventory \n" +
            "WHERE id = ?\n" +
            "returning *;";

    String UPDATE_PRODUCT = "update\n" +
            "\tproducts_inventory \n" +
            "set\n" +
            "\tproduct_name  = ?, \n" +
            "\tavailable_quantity  = ?, \n" +
            "\tproduct_type = ?,\n" +
            "\tprice_without_vat  = ?,\n" +
            "\tprice_with_vat  = ?,\n" +
            "\tin_stock  = ?,\n" +
            "\tsupplier  = ?\n" +
            "where\n" +
            "\tid = ?\n" +
            "returning *;";

    String SAVE_ORDER = "insert\n" +
            "\tinto\n" +
            "\torders (\n" +
            "\tcustomer_id ,\n" +
            "\tis_order_completed ,\n" +
            "\tis_order_paid ,\n" +
            "\tdate_of_order )\n" +
            "values (?,?,?,now())RETURNING *;";

    String GET_ORDER_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\torders\n" +
            "where\n" +
            "\tid in (?)";

    String DELETE_ORDER = "DELETE FROM orders \n" +
            "WHERE id = ?\n" +
            "returning *;";

    String GET_ALL_CUSTOMER_ORDERS = "select\n" +
            "\t*\n" +
            "from\n" +
            "\torders\n" +
            "where\n" +
            "\tid in (?)";

    String UPDATE_ORDER = "update\n" +
            "\torders\n" +
            "set\n" +
            "\tis_order_completed = true,\n" +
            "\tis_order_paid = true,\n" +
            "\tdate_of_order_completed = now()\n" +
            "where\n" +
            "\tid in(?)\n" +
            "returning *;";

    String SAVE_PRODUCT_ORDER = "insert\n" +
            "\tinto\n" +
            "\tproduct_orders (\n" +
            "\torder_id ,\n" +
            "\tproduct_id ,\n" +
            "\tordered_quantity)\n" +
            "values (?,?,?)RETURNING *;";

    String GET_PRODUCT_ORDER_BY_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tproduct_orders\n" +
            "where\n" +
            "\tid in (?)";

    String DELETE_PRODUCT_ORDER = "DELETE FROM product_orders \n" +
            "WHERE id = ?\n" +
            "returning *;";

    String UPDATE_PRODUCT_ORDER = "update\n" +
            "\tproduct_orders \n" +
            "set\n" +
            "\tordered_quantity  = ? \n" +
            "where\n" +
            "\tid = ?\n" +
            "returning *;";

    String GET_PRODUCT_ORDER_BY_ORDER_ID = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tproduct_orders\n" +
            "where\n" +
            "\torder_id in (?)";
}
