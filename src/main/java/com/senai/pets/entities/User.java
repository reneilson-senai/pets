package com.senai.pets.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(optional=true)
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*@Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }*/
}
