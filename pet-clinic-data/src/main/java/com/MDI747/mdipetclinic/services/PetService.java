package com.MDI747.mdipetclinic.services;

import java.util.Set;

import com.MDI747.mdipetclinic.model.Pet;

public interface PetService {
	Pet findById(Long id);
	Pet save(Pet pet);
	Set<Pet> findAll();
}
