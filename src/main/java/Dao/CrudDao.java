package Dao;

import java.util.List;

public interface CrudDao<T> {

    void save(T object);

    void deleteAll();

    T getByID(int id);

    void delete(int id);

    int getRecordsCount();

    int getRandomId();

    List<Integer> getRandomIds(int numberOfIds);

    List getByIDs(List<Integer> ids);

    void truncate();
}
