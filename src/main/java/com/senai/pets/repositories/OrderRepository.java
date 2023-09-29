package com.senai.pets.repositories;

import org.springframework.data.repository.CrudRepository;

import com.senai.pets.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
}
