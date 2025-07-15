package yamen.marcketplace.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import yamen.marcketplace.Entity.ContactUs;
import yamen.marcketplace.Repository.ContactUsRepo;

import java.util.UUID;

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

    public ResponseEntity<?> getAllContacts() {
        return new ResponseEntity<>(contactUsRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<ContactUs> deleteContactById(UUID id) {
        ContactUs myMessage = contactUsRepo.findById(id).orElse(null);
        contactUsRepo.deleteById(id);
        return new ResponseEntity<>(myMessage, HttpStatus.OK) ;

    }

}

