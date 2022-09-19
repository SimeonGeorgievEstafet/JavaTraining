package Helpers.Queries;

public interface SQLQueries {

    String GET_RECORD_COUNT = "select count(*) from %s";

    String UPDATE_RECORD = "update %s set %s where id = %s returning *;";

    String DELETE_ALL_RECORDS = "delete from %s where id is not null";


}
