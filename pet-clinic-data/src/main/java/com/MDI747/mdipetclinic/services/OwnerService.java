package com.MDI747.mdipetclinic.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.MDI747.mdipetclinic.model.Owner;

public interface OwnerService {
	Owner findByLastName(String lastName);
	Owner findById(Long id);
	Owner save(Owner owner);
	Set<Owner> findAll();
}
