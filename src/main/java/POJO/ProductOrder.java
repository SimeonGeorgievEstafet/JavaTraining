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
public class ProductOrder implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    String id;

    @NotNull
    @Column(name = "order_id")
    String orderId;

    @NotNull
    @Column(name = "product_id")
    String productId;

    @Column(name = "ordered_quantity")
    int orderedQuantity;

//    Product product;

    public String toQuery() {
        return ("'" + orderId + "','" +
                productId + "','" +
                orderedQuantity + "'");
    }

    public String toString() {
        return "order_id='" + orderId + '\'' +
                ", product_id='" + productId + '\'' +
                ", ordered_quantity=" + orderedQuantity;
    }
}
