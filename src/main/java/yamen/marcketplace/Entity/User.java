package yamen.marcketplace.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="uzer")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUIDWay")
    @GenericGenerator(name = "UUIDWay", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;
    @Column(unique=true , nullable = false,name = "user-name")
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "first-name")
    private String firstName;
    @Column(name="last-name")
    private String lastName;
    @Column(name = "email" , nullable = false , unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name="OTP")
    private String otp;
    @Column(name = "phone" ,unique = true)
    private String phone;
    @Column(name = "isValid")
    private boolean isValid = false;

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));

    }

}
