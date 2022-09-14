package Dao;

public interface CrudDao<T> {

    void save(T object);

    void deleteAll(String database);

    T getByID(int id);

    void delete(int id);

}
