package com.senai.pets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.pets.entities.Category;
import com.senai.pets.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category){
        Category categoriaCriada = service.create(category);
        return new ResponseEntity<Category>(categoriaCriada, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> put(@RequestBody Category category){
        Category categoriaAtualizada = service.update(category);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getList(@RequestParam String name){
        List<Category> lista = service.list(name);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getRead(@PathVariable Long id){
        Category categoriaEncontrada = service.read(id);
        return ResponseEntity.ok(categoriaEncontrada);
    }
}
