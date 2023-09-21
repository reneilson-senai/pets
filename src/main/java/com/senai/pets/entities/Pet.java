package com.senai.pets.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Pet {
    @Enumerated(EnumType.STRING)
    private Status status;
    private String name;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
