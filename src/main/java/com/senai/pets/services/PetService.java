package com.senai.pets.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.pets.dtos.PetInputDTO;
import com.senai.pets.dtos.PetOutputDTO;
import com.senai.pets.entities.Pet;
import com.senai.pets.entities.User;
import com.senai.pets.repositories.PetRepository;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    @Transactional
    public PetOutputDTO create(PetInputDTO dto, User usuario){
        Pet petTemporario = converterDtoParaEntidade(dto);
        petTemporario.setCreatedBy(usuario);
        Pet petCriado = repository.save(petTemporario);
        return converterEntidadeParaDTO(petCriado);
    }

    public PetOutputDTO converterEntidadeParaDTO(Pet pet){
        PetOutputDTO dtoSaida = new PetOutputDTO();
        dtoSaida.setId(pet.getId());
        dtoSaida.setName(pet.getName());
        dtoSaida.setStatus(pet.getStatus());
        dtoSaida.setUsername(pet.getCreatedBy().getUsername());
        return dtoSaida;
    }

    public Pet converterDtoParaEntidade(PetInputDTO dto){
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setStatus(dto.getStatus());
        return pet;
    }

    public PetOutputDTO read(Long id){
        Pet pet = repository.findById(id).get();
        return converterEntidadeParaDTO(pet);
    }

    public List<PetOutputDTO> list(Pageable page){
        return repository.findAll(page).stream().map(p->converterEntidadeParaDTO(p)).toList();
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public PetOutputDTO update(PetInputDTO pet){
        if(pet.getId() == null){
            pet.setId(99l);
        }
        if(repository.existsById(pet.getId())){
            Pet petAtualizado = repository.save(converterDtoParaEntidade(pet));
            return converterEntidadeParaDTO(petAtualizado);
        }else{
            return null;
        }

    }
}
