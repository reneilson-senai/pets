package com.senai.pets.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class LoginInputDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}

