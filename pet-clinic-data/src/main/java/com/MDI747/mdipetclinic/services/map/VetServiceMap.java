package com.MDI747.mdipetclinic.services.map;

import java.util.Set;

import com.MDI747.mdipetclinic.model.Vet;
import com.MDI747.mdipetclinic.services.CrudService;
import com.MDI747.mdipetclinic.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}
	
	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
	@Override
	public void delete(Vet object) {
		super.delete(object);
	}
}
