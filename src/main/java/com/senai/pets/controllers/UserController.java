package com.senai.pets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.pets.entities.User;
import com.senai.pets.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User usuarioCriado = service.create(user);
        return new ResponseEntity<User>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> put(@RequestBody User user){
        User usuarioAtualizada = service.update(user);
        return ResponseEntity.ok(usuarioAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getList(){
        List<User> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getRead(@PathVariable Long id){
        User usuarioEncontrada = service.read(id);
        return ResponseEntity.ok(usuarioEncontrada);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (User) auth.getPrincipal();
        return ResponseEntity.ok(usuario);
    }
}
