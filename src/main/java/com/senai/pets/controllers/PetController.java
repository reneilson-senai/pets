package com.senai.pets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.pets.dtos.PetInputDTO;
import com.senai.pets.dtos.PetOutputDTO;
import com.senai.pets.entities.Pet;
import com.senai.pets.entities.User;
import com.senai.pets.services.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pets")
public class PetController {
    @Autowired
    private PetService service;

    @PostMapping
    public ResponseEntity<PetOutputDTO> post(@RequestBody @Valid PetInputDTO pet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (User) auth.getPrincipal();

        PetOutputDTO petCriada = service.create(pet, usuario);

        return new ResponseEntity<PetOutputDTO>(petCriada, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PetOutputDTO> put(@RequestBody PetInputDTO pet) {
        PetOutputDTO petAtualizada = service.update(pet);
        return ResponseEntity.ok(petAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PetOutputDTO>> getList(Pageable page) {
        List<PetOutputDTO> lista = service.list(page);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOutputDTO> getRead(@PathVariable Long id) {
        PetOutputDTO petEncontrada = service.read(id);
        return ResponseEntity.ok(petEncontrada);
    }
}
