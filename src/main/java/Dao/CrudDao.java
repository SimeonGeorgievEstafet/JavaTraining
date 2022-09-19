package Dao;

public interface CrudDao<T> {

    void save(T object);

    void deleteAll();

    T getByID(int id);

    void delete(int id);

    void getRecordsCount();
}
