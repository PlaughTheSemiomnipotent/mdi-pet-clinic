package com.MDI747.mdipetclinic.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

//	@RequestMapping({ "", "/", "/index", "/index.html" })
//	public String listOwners(Model model) {
//		model.addAttribute("owners", ownerService.findAll());
//		return "owners/index";
//	}

	@RequestMapping({ "/find" })
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	
	@GetMapping("")
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		// allow parameterless to get all owners
		if(owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}
		
		// find owners by last name
		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if(results.isEmpty()) {
			result.rejectValue("lastName", "not found", "not found");
			return "owners/findOwners";
		} else if(results.size() == 1) {
			// one owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("listOwners", results);
			return "owners/ownersList";
		}
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("owner", Owner.builder().id(1L).build());
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping("/new")
	public String processCreationForm(@Validated Owner owner, BindingResult result) {
		if(result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
	
	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result, @PathVariable Long ownerId) {
		if(result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" +savedOwner.getId();
		}
	}
}
