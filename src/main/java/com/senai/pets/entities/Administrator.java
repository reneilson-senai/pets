package com.senai.pets.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper=true)
@Data @AllArgsConstructor @NoArgsConstructor
public class Administrator extends User {
    private Boolean isAdmin;
    private Office office;
}
