package com.MDI747.mdipetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.model.Pet;
import com.MDI747.mdipetclinic.model.PetType;
import com.MDI747.mdipetclinic.model.Vet;
import com.MDI747.mdipetclinic.services.OwnerService;
import com.MDI747.mdipetclinic.services.PetTypeService;
import com.MDI747.mdipetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dog = new PetType();
		dog.setName("Dog");
		PetType cat = new PetType();
		dog.setName("Cat");
		PetType bird = new PetType();
		dog.setName("Bird");

		PetType savedDogPetType = petTypeService.save(dog);
		PetType savedCatPetType = petTypeService.save(cat);
		PetType savedBirdPetType = petTypeService.save(bird);

		System.out.println("Loaded PetTypes...");

		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickel Ave");
		owner1.setCity("Miami");
		owner1.setTelephone("1231231234");

		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickel Ave");
		owner2.setCity("Miami");
		owner2.setTelephone("1231231234");

		Pet fionnasPet = new Pet();
		fionnasPet.setPetType(savedCatPetType);
		fionnasPet.setOwner(owner2);
		fionnasPet.setBirthDate(LocalDate.now());
		fionnasPet.setName("Just Cat");
		owner1.getPets().add(fionnasPet);

		ownerService.save(owner2);

		System.out.println("Loaded Owners and Pets...");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Chuck");
		vet2.setLastName("Finley");
		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}

}
