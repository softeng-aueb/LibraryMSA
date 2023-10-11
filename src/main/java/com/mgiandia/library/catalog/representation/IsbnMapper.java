package com.mgiandia.library.catalog.representation;

import com.mgiandia.library.catalog.domain.ISBN;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public class IsbnMapper {
	public String toString(ISBN isbn) {
		return isbn == null ? null : isbn.getValue();
	}
}
