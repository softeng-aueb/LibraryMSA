package com.mgiandia.library.representation;

import com.mgiandia.library.loans.domain.Publisher;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PublisherMapper {
	public abstract PublisherRepresentation toRepresentation(Publisher publisher);
	
}
