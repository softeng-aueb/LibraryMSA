package com.mgiandia.library.loans.representation;

import com.mgiandia.library.catalog.representation.BookRepresentation;
import com.mgiandia.library.loans.domain.ItemState;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ItemRepresentation {
	public Integer itemNumber;
	//Developer chooses whether he wants a full representation of an item including the book representation or just the bookId
	public BookRepresentation book;
	public ItemState state;
}
