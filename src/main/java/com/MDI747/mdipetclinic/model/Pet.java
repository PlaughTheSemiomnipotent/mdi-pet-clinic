package com.MDI747.mdipetclinic.model;

import java.time.LocalDate;

public class Pet {
	private LocalDate birthDate;
	private PetType type;
	private Owner owndr;
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public PetType getType() {
		return type;
	}
	public void setType(PetType type) {
		this.type = type;
	}
	public Owner getOwndr() {
		return owndr;
	}
	public void setOwndr(Owner owndr) {
		this.owndr = owndr;
	}
	
	
}
