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
        Pet petCriado = repository.save(pet);
        return petCriado;
    }

    public Pet read(Long id){
        return repository.findById(id).get();
    }

    public List<Pet> list(){
        List<Pet> pets = (List<Pet>) repository.findAll();
        return pets;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Pet update(Pet pet){
        if(repository.existsById(pet.getId())){
            return repository.save(pet);
        }else{
            return null;
        }
    }
}
