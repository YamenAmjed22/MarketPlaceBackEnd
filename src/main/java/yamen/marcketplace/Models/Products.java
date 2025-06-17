package yamen.marcketplace.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(generator = "UUIDWay")
    @GenericGenerator(name = "UUIDWay", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    @Setter
    private UUID id;
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private String productDescription;
    @Getter
    @Setter
    private String productPrice;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String productImage;
    @Getter
    @Setter
    private Long stockQuantity;
    @Getter
    @Setter
    private String rating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @Getter
    @Setter
    private Categories category;

    @Getter
    @Setter
    @Column(name = "product_type")
    private String ProductType;

}
