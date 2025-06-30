package yamen.marcketplace.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String productDescription;
    private String productPrice;
    private String status;
    private String productImage;
    private String productCategory;
    private Long stockQuantity;
    private String rating;
    private UUID categoryId;
}
