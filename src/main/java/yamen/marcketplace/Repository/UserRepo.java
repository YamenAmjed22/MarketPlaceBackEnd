package yamen.marcketplace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo  extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
