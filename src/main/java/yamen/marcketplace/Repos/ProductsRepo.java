package yamen.marcketplace.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Models.Products;

import java.util.UUID;

@Repository
public interface ProductsRepo extends JpaRepository<Products, UUID> {

}
