package yamen.marcketplace.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.ProductInTheCart;
import yamen.marcketplace.Services.ProductInTheCartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/MyCart")
@CrossOrigin("*")
public class ProductInTheCartController {

    @Autowired
    private ProductInTheCartService productInTheCartService;

    @PostMapping
    public ResponseEntity<ProductInTheCart> addProductInTheCart(@RequestBody ProductInTheCart productInTheCart) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        productInTheCart.setUserName(userName);
        return productInTheCartService.addNewProductToTheCart(productInTheCart);
    }

    @GetMapping
    public ResponseEntity<List<ProductInTheCart>> getAllProductsInTheCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<ProductInTheCart> products = productInTheCartService.getAllProductsInTheCartForLoginUser(userName);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductInTheCart> deleteProductInTheCartById(@PathVariable("id") UUID id) {
            return new ResponseEntity<>(productInTheCartService.deleteProductInTheCartById(id) , HttpStatus.OK) ;
    }


}
