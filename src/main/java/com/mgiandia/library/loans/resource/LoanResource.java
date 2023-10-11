package com.mgiandia.library.loans.resource;

import static com.mgiandia.library.loans.resource.LibraryUri.LOANS;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import com.mgiandia.library.loans.domain.Loan;
import com.mgiandia.library.loans.persistence.LoanRepository;
import com.mgiandia.library.loans.representation.LoanMapper;
import com.mgiandia.library.loans.representation.LoanRepresentation;


@Path(LOANS)
public class LoanResource  {
	@Inject
	LoanRepository loanRepository;
	
	@Inject
	LoanMapper loanMapper;
	
	@GET
	@Path("/{loanId}")
	@Transactional
	public  LoanRepresentation find(@PathParam("loanId") Integer loanId) {
		Loan loan = loanRepository.findById(loanId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		return loanMapper.toRepresentation(loan);
	}
	
	@GET
	@Path("/item/{itemId}")
	@Transactional
	public  LoanRepresentation findForItem(@PathParam("itemId") Integer itemId) {
		Loan loan = loanRepository.activeForItem(itemId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		return loanMapper.toRepresentation(loan);
	}

	
	
	
	@POST
	@Path("/{loanId}/returnItem")
	@Transactional
	public Response returnItem(@PathParam("loanId") Integer loanId){
		Loan loan = loanRepository.findById(loanId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		loan.returnItem();
		
		return Response.noContent().build();
	}
	
	
	
}
