package POJO;

import com.github.javafaker.Address;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor

public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id", nullable = false)
    String customerId;

    @Column(name="deactivation_date")
    Date deactivationDate;

    @Column(name="activation_date")
    Date activationDate;

    @NotNull
    @Column(name="name")
    String name;

    @NotNull
    @Column(name="email")
    String email;

    @NotNull
    @Column(name="phone")
    String phone;

    @Column(name="age")
    int age;

    @Column(name="gdpr")
    boolean gdpr;

    @Column(name="customer_profile_status")
    boolean customerProfileStatus;

    @Column(name="reason")
    String reason;

    @Column(name="notes")
    String notes;

    Address address;

    List<Order> order;
}
