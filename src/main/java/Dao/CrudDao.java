package Dao;

import POJO.Customer;

import java.util.List;

public interface CrudDao<T> {

    void save(T object);

    void deleteAll();

    Object getByID(int id);

    void delete(int id);

    void getRecordsCount();

    int getRandomId();

    List<Integer> getRandomIds(int numberOfIds);

    List<Object> getByIDs(List<Integer> ids);
}
