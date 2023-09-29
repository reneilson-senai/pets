package com.senai.pets.repositories;

import org.springframework.data.repository.CrudRepository;

import com.senai.pets.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
    
}
