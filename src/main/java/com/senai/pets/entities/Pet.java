package com.senai.pets.entities;

import java.util.List;

import com.senai.pets.dtos.PetInputDTO;

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
    public Pet(PetInputDTO dto) {
        this.name = dto.getName();
        this.status = dto.getStatus();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne(optional = true)
    private Order order;

    @ManyToOne(optional = true)
    private User createdBy;

    @ManyToMany
    private List<Tag> tags;

    private Boolean isActive = true;
}
