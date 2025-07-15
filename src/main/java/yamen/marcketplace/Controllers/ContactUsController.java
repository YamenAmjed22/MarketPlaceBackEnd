package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.ContactUs;
import yamen.marcketplace.Entity.Role;
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
    public ResponseEntity<ContactUs> deleteContact(@PathVariable UUID id) {
        User user = userService.getUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            if (user.getRole() == Role.ROLE_ADMIN){
                return contactUsService.deleteContactById(id);

            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() ;
            }
        }
    }

}
