package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.ProductDTO;
import yamen.marcketplace.Entity.Products;
import yamen.marcketplace.Entity.Role;
import yamen.marcketplace.Entity.User;
import yamen.marcketplace.Services.ProductsServices;
import yamen.marcketplace.Services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Products> getAllProducts() {
        return productsServices.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable UUID id) {
       return  productsServices.getProductById(id);
    }
    @PostMapping()
    public ResponseEntity<Products> addNewProduct(@RequestBody ProductDTO productDTO) {

        return productsServices.addNewProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId) {
        User user = userService.getUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            if (user.getRole() == Role.ROLE_ADMIN){
                return productsServices.deleteProductById(productId);

            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() ;
            }
        }
    }



}
