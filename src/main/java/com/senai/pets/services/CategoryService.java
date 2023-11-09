package com.senai.pets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.pets.entities.Category;
import com.senai.pets.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category create(Category category){
        Category categoriaCriada = repository.save(category);
        return categoriaCriada;
    }

    public Category update(Category category){
        if(repository.existsById(category.getId())){
            return repository.save(category);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Category read(Long id){
        return repository.findById(id).get();
    }

    public List<Category> list(String name){
        if(name != null){
            return repository.findByName(name);
        }else{
            return repository.findAll();
        }
    }
}
