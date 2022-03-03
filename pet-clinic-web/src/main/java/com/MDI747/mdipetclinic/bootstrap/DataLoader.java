package com.MDI747.mdipetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.model.Pet;
import com.MDI747.mdipetclinic.model.PetType;
import com.MDI747.mdipetclinic.model.Specialty;
import com.MDI747.mdipetclinic.model.Vet;
import com.MDI747.mdipetclinic.model.Visit;
import com.MDI747.mdipetclinic.services.OwnerService;
import com.MDI747.mdipetclinic.services.PetTypeService;
import com.MDI747.mdipetclinic.services.SpecialtyService;
import com.MDI747.mdipetclinic.services.VetService;
import com.MDI747.mdipetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if (count == 0) {
			loadData();
		}
	}

	private void loadData() {
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

		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty dentistry = new Specialty();
		dentistry.setDescription("dentistry");

		Specialty savedRadiology = specialtyService.save(radiology);
		Specialty savedSurgery = specialtyService.save(surgery);
		Specialty savedDentistry = specialtyService.save(dentistry);

		System.out.println("Loaded Specialties...");

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
		owner2.getPets().add(fionnasPet);

		ownerService.save(owner2);

		System.out.println("Loaded Owners and Pets...");

		Visit catVisit = new Visit();
		catVisit.setDate(LocalDate.now());
		catVisit.setDesctiption("Sneezy Kitty");
		catVisit.setPet(fionnasPet);

		visitService.save(catVisit);

		System.out.println("Loaded Visits...");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialties().add(savedRadiology);
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Chuck");
		vet2.setLastName("Finley");
		vet2.getSpecialties().add(savedSurgery);
		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}

}
