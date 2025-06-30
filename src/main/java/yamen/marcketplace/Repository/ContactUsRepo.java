package yamen.marcketplace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Entity.ContactUs;

import java.util.UUID;

@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, UUID> {
}
