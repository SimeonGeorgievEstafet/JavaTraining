package CustomerTests;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDao {

//    List<Customer> getAllCustomers() throws IOException, SQLException;

    void save(Customer customer) throws JsonProcessingException;

    void update(String status, int customerId);

    void delete(int customerId);

    ArrayList<Integer> getRandomIds(int i);

    void deleteAll();
    int getRandomId();

    int getRecordsCount();

    //To be implemented in next story

    Customer getByID(int i);

    List<Customer> getByIDs(List<Integer>ids);

    List<Customer> getByIdResultSetMapper(List<Integer> ids);

    Customer getByIdResultSetMapper(int id);

    Customer getByIdReflection(int id);

    Customer getByIdDbUtils(int id);

    List<Customer> getByIdsDbUtils(List<Integer> ids);
}