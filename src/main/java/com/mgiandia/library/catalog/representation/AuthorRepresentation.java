package com.mgiandia.library.catalog.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AuthorRepresentation {
	public Integer id;
	public String firstName;
    public String lastName;
}
