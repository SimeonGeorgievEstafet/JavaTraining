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
public class Product implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    String id;

    @NotNull
    @Column(name = "product_name")
    String productName;

    @Column(name = "available_quantity")
    int availableQuantity;

    @NotNull
    @Column(name = "product_type")
    String productType;

    @NotNull
    @Column(name = "price_without_vat")
    Double priceWithoutVat;

    @NotNull
    @Column(name = "price_with_vat")
    Double priceWithVat;

    @NotNull
    @Column(name = "in_stock")
    Boolean inStock;

    @Column(name = "supplier")
    int supplier;

    public String toQuery() {
        return ("'" + productName + "','" +
                availableQuantity + "','" +
                productType + "','" +
                priceWithoutVat + "','" +
                priceWithVat + "','" +
                inStock + "','" +
                supplier + "'");
    }

    @Override
    public String toString() {
        return "product_name='" + productName + '\'' +
                ", available_quantity=" + availableQuantity +
                ", product_type='" + productType + '\'' +
                ", price_without_vat=" + priceWithoutVat +
                ", price_with_vat=" + priceWithVat +
                ", in_stock=" + inStock +
                ", supplier=" + supplier;
    }
}
