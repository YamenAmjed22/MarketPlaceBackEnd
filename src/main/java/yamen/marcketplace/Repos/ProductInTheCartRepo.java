package yamen.marcketplace.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Models.ProductInTheCart;

import java.util.UUID;

@Repository
public interface ProductInTheCartRepo extends JpaRepository<ProductInTheCart, UUID> {
}
