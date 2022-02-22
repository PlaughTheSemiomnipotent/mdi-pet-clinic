package com.MDI747.mdipetclinic.services;

import java.util.Set;

import com.MDI747.mdipetclinic.model.Vet;

public interface VetService {
	Vet findById(Long id);
	Vet save(Vet vet);
	Set<Vet> findAll();
}
