package com.MDI747.mdipetclinic.services;

import com.MDI747.mdipetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	Owner findByLastName(String lastName);
}
