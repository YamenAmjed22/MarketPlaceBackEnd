package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yamen.marcketplace.Models.Categories;
import yamen.marcketplace.Models.FilesUploaded;
import yamen.marcketplace.Models.ProductDTO;
import yamen.marcketplace.Models.Products;
import yamen.marcketplace.Repos.CategoriesRepo;
import yamen.marcketplace.Repos.FileUploadedRepo;
import yamen.marcketplace.Repos.ProductsRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductsServices {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private FileUploadedRepo fileUploadedRepo;

    public List<Products> getAllProducts() {
        return productsRepo.findAll();
    }

    public ResponseEntity<?> saveImage(MultipartFile imageFile) {
        try {
            String uploadDir = "src/main/resources/uploads";
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir , fileName);
            Files.createDirectories(filePath.getParent());
            // this is line save my img physically as byte on my disk
            Files.write(filePath, imageFile.getBytes());

            FilesUploaded filesUploaded = new FilesUploaded();
            filesUploaded.setFileName(fileName);
            filesUploaded.setFileType(imageFile.getContentType());
            filesUploaded.setFilePath(filePath.toString());
            fileUploadedRepo.save(filesUploaded);

            return new ResponseEntity<>("http://localhost:9091/api/uploadfile/image/" + filesUploaded.getId(),HttpStatus.ACCEPTED);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }


    public ResponseEntity<Products> addNewProduct(ProductDTO productDTO) {
        Products newProduct = new Products();
        newProduct.setProductName(productDTO.getProductName());
        newProduct.setProductDescription(productDTO.getProductDescription());
        newProduct.setProductPrice(productDTO.getProductPrice());
        newProduct.setStatus(productDTO.getStatus());
        newProduct.setProductImage(productDTO.getProductImage());
        newProduct.setStockQuantity(productDTO.getStockQuantity());
        newProduct.setRating(productDTO.getRating());




        // ✅ Correct way: Fetch the category from DB to avoid TransientObjectException
        Categories category = categoriesRepo.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        newProduct.setCategory(category); // safe, managed entity
        newProduct.setProductType(category.getName());
        return new ResponseEntity<Products>(productsRepo.save(newProduct), HttpStatus.CREATED);
    }


    public ResponseEntity<Products> deleteProductById(UUID  productId) {
        Products deletedProduct = productsRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        productsRepo.deleteById(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }


}
