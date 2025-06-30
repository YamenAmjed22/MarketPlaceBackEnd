package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yamen.marcketplace.Entity.LoginDTO;
import yamen.marcketplace.Entity.OtpCheckRequestDto;
import yamen.marcketplace.Entity.Role;
import yamen.marcketplace.Entity.User;
import yamen.marcketplace.Repository.UserRepo;

import java.util.Optional;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;



    public String generateOtp(){
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }

    public ResponseEntity<?> addNewUser(User newUser) {
        try {

            newUser.setRole(Role.ROLE_USER);

            if (userRepo.findByUserName(newUser.getUsername()).isPresent()) {
                return new ResponseEntity<>("\" user name already exist \"",HttpStatus.CONFLICT);
            }
            else if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
                return new ResponseEntity<>("\" email already exist \"",HttpStatus.CONFLICT);
            }
            else {
                newUser.setOtp(generateOtp());
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                User newUserSaved = userRepo.save(newUser);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(newUser.getEmail());
                message.setSubject("Thank You For Registration In My Marketplace");
                message.setText("Dear " + newUser.getFirstName() + " " + newUser.getLastName() + ",\n\n" +
                        "Thank you for registering on my web app:\n\n" +
                        "This is your OTP: " + newUserSaved.getOtp() + "\n\nBest regards,\nYamen Amjed Rasmi Mustafa");

                mailSender.send(message);
            }

            return new ResponseEntity<>("\""+newUser.getEmail()+"\"", HttpStatus.CREATED);

        }catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>("\"The User Not Saved !\"", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> checkOtp(OtpCheckRequestDto otpCheckRequestDto) {
        User user = userRepo.findByEmail(otpCheckRequestDto.getEmail()).orElseThrow(
                () -> new RuntimeException("\"There is no User with this email address !\"")
        );

        if (user.getOtp().equals(otpCheckRequestDto.getOtp())) {
            user.setValid(true);
            user.setOtp(null);
            userRepo.save(user);
            return ResponseEntity.ok("\"Your OTP is valid.\"");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Your OTP is not valid.");
        }
    }

    public ResponseEntity<?> loginUser(LoginDTO  loginRequest) {
        Optional<User> registeredUser = userRepo.findByUserName(loginRequest.getUserName());

        if (registeredUser.isPresent()) {
            if (passwordEncoder.matches(loginRequest.getPassword(),registeredUser.get().getPassword()) && registeredUser.get().isValid()) {
                String token = jwtService.generateToken(registeredUser.get());
                return new ResponseEntity<>("\""+ token+"\"" , HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("\"Your username and password are incorrect.\"", HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>("\"Your username is not correct.\"", HttpStatus.UNAUTHORIZED);
        }
    }



}
