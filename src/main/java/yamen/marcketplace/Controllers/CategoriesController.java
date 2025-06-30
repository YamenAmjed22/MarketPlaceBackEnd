package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.Categories;
import yamen.marcketplace.Services.CategoriesServices;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoriesController {


    @Autowired
    private CategoriesServices categoriesServices;


    @GetMapping()
    public List<Categories> getAllCategories() {
        return categoriesServices.getAllCategories();
    }

    @PostMapping()
    public ResponseEntity<Categories> addNewCategory(@RequestBody CategoryDTO categoryDTO) {


        return categoriesServices.addNewCategory(categoryDTO);
    }


}
