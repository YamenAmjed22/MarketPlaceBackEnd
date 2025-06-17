package yamen.marcketplace.Controllers;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryDTO {

    private String name;
    private String imageUrl;
    private String categoriesType;
    private UUID parentId;
}
