package com.senai.pets.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.pets.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByQuantity(Integer quantity);
}
