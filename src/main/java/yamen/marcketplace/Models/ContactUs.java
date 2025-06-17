package yamen.marcketplace.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ContactUs {

    @Id
    @GeneratedValue(generator = "UUIDWay")
    @GenericGenerator(name = "UUIDWay", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column( nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private  String subject;
    @Column(nullable = false)
    private String message;
}
