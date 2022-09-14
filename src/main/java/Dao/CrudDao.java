package Dao;

import POJO.Product;

public interface CrudDao<T> {

    void save(T object);

    T getByID(int id);

    void delete(int id);

}
