package com.senai.pets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senai.pets.dtos.LoginInputDTO;
import com.senai.pets.dtos.TokenDTO;
import com.senai.pets.entities.User;
import com.senai.pets.services.TokenService;

import jakarta.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginInputDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dados.getLogin(),
                dados.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());
        var tokenResponse = new TokenDTO(token);
        return new ResponseEntity<TokenDTO>(
                tokenResponse,
                HttpStatus.CREATED);
    }

}
