package com.mgiandia.library.loans.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class BorrowerRepresentation {
	public Integer borrowerNo;
	public String firstName;
	public String lastName;
	public String email;
	public BorrowerCategoryRepresentation category; 
	
}
