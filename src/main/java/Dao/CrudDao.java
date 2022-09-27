package Dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {

    void save(T object);

    void deleteAll();

    Object getByID(int id);

    void delete(int id);

    void getRecordsCount();

    int getRandomId();

    List<Integer> getRandomIds(int numberOfIds);

    List<Object> getByIDs(List<Integer> ids) throws SQLException;
}
