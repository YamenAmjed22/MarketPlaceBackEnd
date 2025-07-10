package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.Entity.LoginDTO;
import yamen.marcketplace.Entity.OtpCheckRequestDto;
import yamen.marcketplace.Entity.User;
import yamen.marcketplace.Repository.UserRepo;
import yamen.marcketplace.Services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService UserService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addnewuser")
    public ResponseEntity<?> addNewUser(@RequestBody User newUser) {
            return UserService.addNewUser(newUser);
    }
    @PostMapping("/ckeckotp")
    public ResponseEntity<?> checkOtp(@RequestBody OtpCheckRequestDto otpCheckRequestDto) {
        return userService.checkOtp(otpCheckRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Print username
        System.out.println("User: " + auth.getName());

        // Print roles
        System.out.println("Roles:");
        auth.getAuthorities().forEach(authority -> System.out.println(authority.getAuthority()));
       return userService.loginUser(loginRequest);
    }
}
