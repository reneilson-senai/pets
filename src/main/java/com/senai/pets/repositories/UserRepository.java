package com.senai.pets.repositories;

import org.springframework.data.repository.CrudRepository;

import com.senai.pets.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
