package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import yamen.marcketplace.Models.ContactUs;
import yamen.marcketplace.Repos.ContactUsRepo;

import java.net.http.HttpRequest;

@Service
public class ContactServices {


    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ContactUsRepo contactUsRepo;


    public ResponseEntity<?> sendContact(ContactUs myMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(myMessage.getEmail());
        message.setSubject("Thank You For Contact To Me");
        message.setText("Dear " + myMessage.getName() + ",\n\n" +
                "Thank you for Your Contact we will see your contact and response on \n\n" +
                 "\n\nBest regards,\nYamen Amjed Rasmi Mustafa");

        mailSender.send(message);

        contactUsRepo.save(myMessage);

        return new ResponseEntity<>("\""+myMessage.getEmail()+"\"", HttpStatus.CREATED);

    }

}

