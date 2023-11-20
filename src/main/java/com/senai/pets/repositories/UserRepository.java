package com.senai.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.pets.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    //public UserDetails findByUsername(String username);
}
