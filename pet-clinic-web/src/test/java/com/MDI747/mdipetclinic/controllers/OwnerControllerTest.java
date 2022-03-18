package com.MDI747.mdipetclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.MDI747.mdipetclinic.model.Owner;
import com.MDI747.mdipetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController controller;

	MockMvc mockMvc;

	Set<Owner> owners;

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

//	@Test
//	void listOwners() throws Exception {
//		when(ownerService.findAll()).thenReturn(owners);
//
//		mockMvc.perform(get("/owners"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("owners/index"))
//			.andExpect(model().attribute("owners", Matchers.hasSize(2)));
//	}

//	@Test
//	void listOwnersByIndex() throws Exception {
//		when(ownerService.findAll()).thenReturn(owners);
//
//		mockMvc.perform(get("/owners/index"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("owners/index"))
//			.andExpect(model().attribute("owners", Matchers.hasSize(2)));
//	}

	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(get("/owners/123"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", Matchers.hasProperty("id", is(1L))));
	}

	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void processFindFormReturnMany() throws Exception {
		List<Owner> multiOwnerList = new ArrayList<>();
		multiOwnerList.add(Owner.builder().id(1L).build());
		multiOwnerList.add(Owner.builder().id(2L).build());

		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(multiOwnerList);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("listOwners", Matchers.hasSize(2)));
	}
	
	@Test
	void processFindFormReturnOne() throws Exception {
		List<Owner> singleOwnerList = new ArrayList<>();
		singleOwnerList.add(Owner.builder().id(1L).lastName("Westen").build());
		
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(singleOwnerList);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
	}
	
	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
		
		verifyNoInteractions(ownerService);
	}

	@Test
	void processCreationForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/1/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
		
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void processUpdateOwnerForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/1/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}

}
