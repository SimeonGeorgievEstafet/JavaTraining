package POJO;

import Dao.CustomerAddressDao;
import com.github.javafaker.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor

public class Customer implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    String id;

    @Column(name = "deactivation_date")
    Date deactivationDate;

    @Column(name = "activation_date")
    Date activationDate;

    @NotNull
    @Column(name = "name")
    String name;

    @NotNull
    @Column(name = "email")
    String email;

    @NotNull
    @Column(name = "phone")
    String phone;

    @Column(name = "age")
    int age;

    @Column(name = "gdpr")
    boolean gdpr;

    @Column(name = "customer_profile_status")
    boolean customerProfileStatus;

    @Column(name = "reason")
    String reason;

    @Column(name = "notes")
    String notes;


    List<Order> order;

    public String toQuery() {
        return ("'" + name + "','" +
                email + "','" +
                phone + "'," +
                age + ",'" +
                gdpr + "','" +
                customerProfileStatus + "','" +
                reason + "','" +
                notes + "'");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getAge() == customer.getAge() && isGdpr() == customer.isGdpr() && isCustomerProfileStatus() == customer.isCustomerProfileStatus() && getName().equals(customer.getName()) && getEmail().equals(customer.getEmail()) && getPhone().equals(customer.getPhone()) && Objects.equals(getReason(), customer.getReason()) && Objects.equals(getNotes(), customer.getNotes());
    }

    public CustomerAddress getCustomerAddress() {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        return customerAddressDao.getByID(Integer.parseInt(id));
    }
}
