package yamen.marcketplace.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "ProductInTheCart")
@Getter
@Setter
public class ProductInTheCart {
    @Id
    @GeneratedValue(generator = "UUIDWay")
    @GenericGenerator(name = "UUIDWay", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String status;
    private String productImage;
    private Long stockQuantity;
    private String rating;
    private String userName;

    @Column(name = "product_type")
    private String ProductType;

}
