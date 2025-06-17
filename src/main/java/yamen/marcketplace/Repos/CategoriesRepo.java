package yamen.marcketplace.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import yamen.marcketplace.Models.Categories;

import java.util.UUID;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, UUID> {
}
