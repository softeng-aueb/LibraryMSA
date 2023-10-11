package com.mgiandia.library.loans.representation;

import com.mgiandia.library.loans.domain.Loan;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = {BorrowerMapper.class, ItemMapper.class})
public abstract class LoanMapper {
	
	
	public abstract LoanRepresentation toRepresentation(Loan loan);
	
	
	
	
}
