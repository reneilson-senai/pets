package com.senai.pets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.pets.entities.Address;
import com.senai.pets.repositories.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    @Transactional
    public Address create(Address address){
        return repository.save(address);
    }

    public List<Address> list(){
        return (List<Address>) repository.findAll();
    }

    @Transactional
    public Address update(Long id, Address address){
        address.setId(id);
        return repository.save(address);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public Address read(Long id){
        if(repository.existsById(id)){
            return repository.findById(id).get();
        }
        return null;
    }
}
