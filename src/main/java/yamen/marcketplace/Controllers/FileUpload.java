package yamen.marcketplace.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yamen.marcketplace.Entity.FilesUploaded;
import yamen.marcketplace.Repository.FileUploadedRepo;
import yamen.marcketplace.Services.ProductsServices;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploadfile")
@CrossOrigin("*")
public class FileUpload {

    @Autowired
    private ProductsServices productsServices;

    @Autowired
    private FileUploadedRepo fileUploadedRepo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewFile( @RequestPart("file") MultipartFile imageFile) {

         return productsServices.saveImage(imageFile);
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImageById(@PathVariable UUID id) throws IOException {
        FilesUploaded image = fileUploadedRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        Path filePath = Paths.get(image.getFilePath()).normalize();
        UrlResource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body((Resource) resource);
    }


}
