package Databases;

public interface SQLQueries {

    String GET_RANDOM_CUSTOMER = "select\n" +
            "\tcustomer_id\n" +
            "from\n" +
            "\tcustomers_1\n" +
            "order by\n" +
            "\trandom()\n" +
            "limit 1";
}
