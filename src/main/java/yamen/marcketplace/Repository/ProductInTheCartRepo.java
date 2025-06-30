package yamen.marcketplace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Entity.ProductInTheCart;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductInTheCartRepo extends JpaRepository<ProductInTheCart, UUID> {

    List<ProductInTheCart> findByUserName(String userName);
}
