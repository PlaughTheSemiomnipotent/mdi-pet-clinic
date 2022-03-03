package com.MDI747.mdipetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.MDI747.mdipetclinic.model.Specialty;
import com.MDI747.mdipetclinic.model.Vet;
import com.MDI747.mdipetclinic.services.SpecialtyService;
import com.MDI747.mdipetclinic.services.VetService;

@Service
@Profile({ "default", "map" })
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

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
		if (object != null) {
			if (object.getSpecialties() != null) {
				object.getSpecialties().forEach(specialty -> {
					if (specialty.getId() == null) {
						Specialty savedSpecialty = specialtyService.save(specialty);
						specialty.setId(savedSpecialty.getId());
					}
				});
			}
			return super.save(object);
		} else {
			return null;
		}
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
