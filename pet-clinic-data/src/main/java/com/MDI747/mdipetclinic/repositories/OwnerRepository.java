package com.MDI747.mdipetclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.MDI747.mdipetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);
}
