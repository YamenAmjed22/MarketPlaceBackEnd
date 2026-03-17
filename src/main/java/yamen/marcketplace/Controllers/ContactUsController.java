package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.ContactUs;
import yamen.marcketplace.Enum.Role;
import yamen.marcketplace.Entity.User;
import yamen.marcketplace.Services.ContactServices;
import yamen.marcketplace.Services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ContactUsController {

    @Autowired
    ContactServices contactUsService;

    @Autowired
    UserService userService;


    @PostMapping("/contact")
    public ResponseEntity<?> addNewContact(@RequestBody ContactUs contactUs) {
        return contactUsService.sendContact(contactUs);
    }
    @GetMapping("/contact")
    public ResponseEntity<?> getAllContacts() {
        return contactUsService.getAllContacts();
    }
    @DeleteMapping("/contact/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ContactUs> deleteContact(@PathVariable UUID id) {

        return contactUsService.deleteContactById(id);


        }
    }


