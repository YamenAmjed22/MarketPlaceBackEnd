package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.DTO.LoginDTO;
import yamen.marcketplace.Services.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO adminUser) {
        return userService.loginAdminUser(adminUser);

    }


}
