package com.dwbook.phonebook.representations;

import io.dropwizard.validation.ValidationMethod;

import org.hibernate.validator.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Contact {
	// private final int id;
	// private final String firstName;
	// private final String lastName;
	// private final String phone;

	private int id;
	
	@NotBlank
	@Length(min=2, max=255)
	private String firstName;
	
	@NotBlank
	@Length(min=2, max=255)
	private String lastName;
	
	@NotBlank
	@Length(min=2, max=30)
	private String phone;

	public Contact() {
		this.id = 0;
		this.firstName = null;
		this.lastName = null;
		this.phone = null;
	}

	public Contact(int id, String firstName, String lastName, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	@JsonIgnore
	@ValidationMethod(message="John Doe is not a valid person!")
	public boolean isValidPerson(){
		if(firstName.equals("John")&&lastName.equals("Doe")){
			return false;
		}else{
			return true;
		}
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
