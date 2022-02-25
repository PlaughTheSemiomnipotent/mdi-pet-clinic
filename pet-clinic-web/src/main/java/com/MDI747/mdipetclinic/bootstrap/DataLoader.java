package com.MDI747.mdipetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.model.Vet;
import com.MDI747.mdipetclinic.services.OwnerService;
import com.MDI747.mdipetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader(OwnerService ownerService, VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		ownerService.save(owner2);

		System.out.println("Loaded Owners...");

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
