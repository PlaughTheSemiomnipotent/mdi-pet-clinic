package com.MDI747.mdipetclinic.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.repositories.OwnerRepository;
import com.MDI747.mdipetclinic.repositories.PetRepository;
import com.MDI747.mdipetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerJpaService service;

	String lastName = "Flintstone";

	Owner buildOwner;

	@BeforeEach
	void setUp() throws Exception {
		buildOwner = Owner.builder().id(1L).lastName(lastName).build();
	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = new HashSet<>();
		ownerSet.add(Owner.builder().id(1L).lastName(lastName).build());
		ownerSet.add(Owner.builder().id(2L).build());
		when(ownerRepository.findAll()).thenReturn(ownerSet);
		Set<Owner> owners = service.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void findById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(buildOwner));
		Owner owner = service.findById(1L);
		assertNotNull(owner);
	}

	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Owner owner = service.findById(1L);
		assertNull(owner);
	}

	@Test
	void save() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(buildOwner);
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}

	@Test
	void delete() {
		service.delete(buildOwner);
		verify(ownerRepository).delete(any());
	}

	@Test
	void deleteById() {
		service.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());

	}

	@Test
	void findByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(buildOwner);
		Owner findOwner = service.findByLastName(lastName);
		assertEquals(lastName, findOwner.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}
