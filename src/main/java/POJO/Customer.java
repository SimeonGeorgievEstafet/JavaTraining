package POJO;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor

public class Customer implements Serializable {

    @Id
    @Column(name = "customerId", nullable = false)
    String customerId;

    Date deactivation_date;
    Date activation_date;

    @NotNull
    String name;
    String email;
    String phone;
    int age;
    boolean gdpr;
    boolean customer_profile_status;
    String reason;
    String notes;

}
