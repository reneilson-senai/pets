package com.senai.pets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.pets.entities.Pet;
import com.senai.pets.repositories.PetRepository;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    @Transactional
    public Pet create(Pet pet){
        return repository.save(pet);
    }

    public List<Pet> list(){
        return (List<Pet>) repository.findAll();
    }

    @Transactional
    public Pet update(Long id, Pet pet){
        pet.setId(id);
        return repository.save(pet);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public Pet read(Long id){
        if(repository.existsById(id)){
            return repository.findById(id).get();
        }
        return null;
    }
}
