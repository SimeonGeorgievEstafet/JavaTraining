package POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class CustomerAddress implements Serializable {

    @Id
    @Column(name = "customer_address_id", nullable = false)
    String customerAddressId;

    @NotNull
    @Column(name = "address")
    String address;

    @NotNull
    @Column(name = "city")
    String city;

    @NotNull
    @Column(name = "province")
    String province;

    @NotNull
    @Column(name = "state")
    String state;

    @Column(name = "postal_code")
    int postalCode;

    @NotNull
    @Column(name = "country")
    String country;


}
