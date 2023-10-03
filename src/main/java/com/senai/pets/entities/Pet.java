package com.senai.pets.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne(optional = true)
    private Order order;

    @ManyToOne(optional = false)
    private Administrator createdBy;

    @ManyToMany
    private List<Tag> tags;

    private Boolean isActive = true;
}
