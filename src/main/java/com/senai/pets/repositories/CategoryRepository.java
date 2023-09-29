package com.senai.pets.repositories;

import org.springframework.data.repository.CrudRepository;

import com.senai.pets.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    
}
