package com.senai.pets.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.pets.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    public List<Category> findByName(String name);
}
