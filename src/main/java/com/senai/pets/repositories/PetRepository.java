package com.senai.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.pets.entities.Pet;
import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Long>{
    public List<Pet> findByNameAndStatus(String n, String s);
}
