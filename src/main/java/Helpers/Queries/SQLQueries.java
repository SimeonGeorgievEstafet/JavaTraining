package Helpers.Queries;

public interface SQLQueries {

    String GET_RECORD_COUNT = "select count(*) from %s";

    String UPDATE_RECORD = "update %s set %s where id = %s returning *;";

    String DELETE_ALL_RECORDS = "delete from %s where id is not null";

    String GET_RANDOM_ID = "select id from %s order by random() limit 1";

    String GET_RANDOM_IDS = "select id from %s order by random() limit %s";

    String GET_BY_ID = "select * from %s where id = %s";

    String GET_BY_IDS = "select * from %s where id in (%s)";


}
