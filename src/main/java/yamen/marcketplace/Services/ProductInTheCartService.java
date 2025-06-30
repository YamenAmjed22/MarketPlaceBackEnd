package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yamen.marcketplace.Entity.ProductInTheCart;
import yamen.marcketplace.Repository.ProductInTheCartRepo;

import java.util.List;
import java.util.UUID;

@Service
public class ProductInTheCartService {

    @Autowired
    private ProductInTheCartRepo productInTheCartRepo;


    public ResponseEntity<ProductInTheCart> addNewProductToTheCart(ProductInTheCart productInTheCart) {
        return new ResponseEntity<>(productInTheCartRepo.save(productInTheCart), HttpStatus.CREATED);
    }

    public List<ProductInTheCart> getAllProductsInTheCartForLoginUser(String userName ) {

        return productInTheCartRepo.findByUserName(userName) ;
    }

    public ProductInTheCart deleteProductInTheCartById(UUID id) {
        ProductInTheCart deletedProduct = productInTheCartRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productInTheCartRepo.deleteById(id);
        return deletedProduct;
    }


}
