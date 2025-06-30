package yamen.marcketplace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Entity.Categories;

import java.util.UUID;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, UUID> {
}
