package com.senai.pets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.senai.pets.dtos.PetInputDTO;
import com.senai.pets.dtos.PetOutputDTO;
import com.senai.pets.entities.Pet;
import com.senai.pets.entities.Status;
import com.senai.pets.repositories.PetRepository;
import com.senai.pets.services.PetService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PetsServiceTest {

    @InjectMocks
    private PetService service;

    @Mock
    private PetRepository repository;

    @Test
    void petAtualizadoComSucesso() {

        PetInputDTO petInput = new PetInputDTO(1l, "bininha", Status.SOLD);

        Pet pet = new Pet(petInput);
        pet.setId(1l);
         
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any())).thenReturn(pet);

        PetOutputDTO petAtualizado = service.update(petInput);

        assertEquals(petInput.getId(), petAtualizado.getId());
        assertEquals(petInput.getName(), petAtualizado.getName());
        assertEquals(petInput.getStatus(), petAtualizado.getStatus());
        
    } 

    @Test
    void petInputSemId() {

        PetInputDTO petInput = new PetInputDTO(null, "bininha", null);

        PetOutputDTO petAtualizado = service.update(petInput);

        assertNull(petAtualizado);
    }

    @Test
    void petNaoEncontrado() {

        PetInputDTO petInput = new PetInputDTO(1l, "bininha", Status.SOLD);
 
        when(repository.existsById(anyLong())).thenReturn(false);
        
        PetOutputDTO petAtualizado = service.update(petInput);

        assertNull(petAtualizado);
        
    } 
}
