package yamen.marcketplace.Controllers;

import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yamen.marcketplace.Models.ProductDTO;
import yamen.marcketplace.Models.Products;
import yamen.marcketplace.Services.ProductsServices;

import java.lang.ref.ReferenceQueue;
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

    @PostMapping()
    public ResponseEntity<Products> addNewProduct(@RequestBody ProductDTO productDTO) {

        return productsServices.addNewProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Products> deleteProduct(@PathVariable("productId") UUID productId) {
        return productsServices.deleteProductById(productId);
    }



}
