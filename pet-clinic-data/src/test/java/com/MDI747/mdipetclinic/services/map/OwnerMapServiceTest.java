package com.MDI747.mdipetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.MDI747.mdipetclinic.model.Owner;

class OwnerMapServiceTest {

	OwnerMapService ownerMapService;

	Long owner1 = 1L;
	Long owner2 = 2L;
	String lastName = "Flintstone";

	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

		ownerMapService.save(Owner.builder().id(owner1).lastName(lastName).build());
	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertNotNull(ownerSet);
		assertEquals(1, ownerSet.size());
	}

	@Test
	void findById() {
		Owner owner = ownerMapService.findById(owner1);
		assertNotNull(owner);
		assertEquals(owner1, owner.getId());
	}

	@Test
	void saveExistingId() {
		Owner owner = Owner.builder().id(owner2).build();
		Owner ownerSaved = ownerMapService.save(owner);
		assertNotNull(ownerSaved);
		assertEquals(owner2, ownerSaved.getId());
	}

	@Test
	void saveNoId() {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void delete() {
		ownerMapService.delete(ownerMapService.findById(owner1));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void deleteById() {
		ownerMapService.deleteById(owner1);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void findByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
	}

	@Test
	void findByLastNameNotFound() {
		Owner owner = ownerMapService.findByLastName("Rubble");
		assertNull(owner);
	}

}
