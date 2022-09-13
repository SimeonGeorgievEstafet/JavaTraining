package Dao;

import POJO.Product;

public interface CrudDao {
    void save(Object object);

    Object getByID(int id);

    void delete(int id);

}
