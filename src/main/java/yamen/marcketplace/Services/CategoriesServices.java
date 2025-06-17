package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import yamen.marcketplace.Controllers.CategoryDTO;
import yamen.marcketplace.Models.Categories;
import yamen.marcketplace.Repos.CategoriesRepo;

import java.util.List;

@Service
public class CategoriesServices {

    @Autowired
    private CategoriesRepo categoriesRepo;

    public List<Categories> getAllCategories() {
        return categoriesRepo.findAll();
    }

    public ResponseEntity<Categories> addNewCategory(CategoryDTO categoryDTO) {
        Categories newCategory = new Categories();
        newCategory.setName(categoryDTO.getName());
        newCategory.setImageUrl(categoryDTO.getImageUrl());
        newCategory.setCategoriesType(categoryDTO.getCategoriesType());
        if (categoryDTO.getParentId() != null) {
            Categories parent = categoriesRepo.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            newCategory.setParent(parent);
        }
        return new ResponseEntity<Categories>(categoriesRepo.save(newCategory) , HttpStatus.CREATED);
    }
}
