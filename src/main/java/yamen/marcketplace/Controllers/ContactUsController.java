package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Models.ContactUs;
import yamen.marcketplace.Services.ContactServices;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ContactUsController {

    @Autowired
    ContactServices contactUsService;


    @PostMapping("/contact")
    public ResponseEntity<?> addNewContact(@RequestBody ContactUs contactUs) {
        return contactUsService.sendContact(contactUs);
    }
}
