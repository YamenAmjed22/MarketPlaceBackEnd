package yamen.marcketplace.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamen.marcketplace.DTO.LoginDTO;
import yamen.marcketplace.DTO.OtpCheckRequestDto;
import yamen.marcketplace.Entity.User;
import yamen.marcketplace.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService UserService;

    @Autowired
    private UserService userService;

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

//        what is the user in security context ?

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("User: " + auth.getName()); *user name
//        System.out.println("Roles:");    *role
//        auth.getAuthorities().forEach(authority -> System.out.println(authority.getAuthority()));
        return userService.loginUser(loginRequest);
    }

    @GetMapping("getall")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
