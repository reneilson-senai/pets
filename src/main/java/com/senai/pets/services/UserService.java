package com.senai.pets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.pets.entities.User;
import com.senai.pets.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Transactional
    public User create(User user){
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        User userCriado = repository.save(user);
        return userCriado;
    }

    public User read(Long id){
        return repository.findById(id).get();
    }

    public List<User> list(){
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public User update(User user){
        if(repository.existsById(user.getId())){
            return repository.save(user);
        }else{
            return null;
    }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
            .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }
}
