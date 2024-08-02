package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.domain.entities.Vaccin;
import br.com.amigofiel.exceptions.NotFoundException;
import br.com.amigofiel.mappers.AnimalMapper;
import br.com.amigofiel.repositories.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public Animal createAnimal(AnimalDTO animalDTO) {
        Animal newAnimal = animalMapper.toEntity(animalDTO);
        return animalRepository.save(newAnimal);
    }

    public AnimalDTO findAnimalById(Long id) {
        return animalMapper.toDTO(animalRepository.findById(id).orElseThrow(() -> new NotFoundException("Animal not found")));
    }

    public List<AnimalDTO> findAllAnimals(){
        List<Animal> animals = animalRepository.findAll();
        return animals.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    public Animal updateAnimal(Long id, AnimalDTO animalDTO) {
        Animal updatedAnimal = animalRepository.findById(id).orElseThrow(() -> new NotFoundException("Animal not found"));

        updatedAnimal.setName(animalDTO.name());
        updatedAnimal.setSpecie(animalDTO.specie());
        updatedAnimal.setBreed(animalDTO.breed());
        updatedAnimal.setBirthDate(animalDTO.birthDate());
        updatedAnimal.setSex(animalDTO.sex());
        updatedAnimal.setWeight(animalDTO.weight());
        updatedAnimal.setSize(animalDTO.size());
        updatedAnimal.setNeutered(animalDTO.neutered());
        updatedAnimal.setAddress(animalDTO.address());
        updatedAnimal.setRegistrationDate(animalDTO.registrationDate());
        updatedAnimal.setCurrentStatus(animalDTO.currentStatus());
        updatedAnimal.setVaccins(animalDTO.vaccins());
        updatedAnimal.setAdoption(animalDTO.adoption());

        return animalRepository.save(updatedAnimal);
    }

    public void deleteAnimalById(long id) {
        animalRepository.deleteById(id);
    }
}
