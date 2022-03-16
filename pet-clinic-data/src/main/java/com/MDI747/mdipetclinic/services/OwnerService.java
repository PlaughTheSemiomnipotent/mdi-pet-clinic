package com.MDI747.mdipetclinic.services;

import java.util.List;

import com.MDI747.mdipetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	Owner findByLastName(String lastName);

	List<Owner> findAllByLastNameLike(String lastName);
}
