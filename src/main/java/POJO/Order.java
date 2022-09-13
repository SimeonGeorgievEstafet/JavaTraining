package POJO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(name = "order_id", nullable = false)
    int orderId;

    @Column(name = "customer_id")
    int customerId;

    @NotNull
    @Column(name = "is_order_completed")
    Boolean isOrderCompleted;

    @NotNull
    @Column(name = "is_order_paid")
    Boolean isOrderPaid;

    @Column(name = "date_of_order")
    Date dateOfOrder;

    @Column(name = "date_of_order_completed")
    Date isOrderPayedCompleted;

}