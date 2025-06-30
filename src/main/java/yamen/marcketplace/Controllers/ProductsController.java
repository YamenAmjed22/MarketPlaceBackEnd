package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.ProductDTO;
import yamen.marcketplace.Entity.Products;
import yamen.marcketplace.Services.ProductsServices;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

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
    public ResponseEntity<Products> deleteProduct(@PathVariable("productId") UUID productId) {
        return productsServices.deleteProductById(productId);
    }



}
