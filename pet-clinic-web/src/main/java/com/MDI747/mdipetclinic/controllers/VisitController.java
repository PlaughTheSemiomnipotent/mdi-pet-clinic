package com.MDI747.mdipetclinic.controllers;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.MDI747.mdipetclinic.model.Pet;
import com.MDI747.mdipetclinic.model.Visit;
import com.MDI747.mdipetclinic.services.PetService;
import com.MDI747.mdipetclinic.services.VisitService;

@Controller
public class VisitController {
	private final VisitService visitService;
	private final PetService petService;
	
	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		
		dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
					setValue(LocalDate.parse(text));
			}
		});
	}
	
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
		Pet pet = petService.findById(petId);
		model.addAttribute("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPet(pet);
		return visit;
	}
	
	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable Long petId, Model model) {
		return "pets/createOrUpdateVisitForm";
	}
	
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String porcessNewVisitForm(@Validated Visit visit, BindingResult result) {
		if(result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		} else {
			visitService.save(visit);
			return "redirect:/owners/{ownerId}";
		}
	}
}
