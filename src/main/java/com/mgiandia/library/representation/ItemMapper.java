package com.mgiandia.library.representation;

import java.util.List;

import com.mgiandia.library.loans.domain.Item;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses= {BookMapper.class})
public abstract class ItemMapper {
	
	
	public abstract ItemRepresentation toRepresentation(Item item);
	
	public abstract List<ItemRepresentation> toRepresentationList(List<Item> item);
	
	
}
