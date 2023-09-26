package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BOOKS;

import java.util.List;

import com.mgiandia.library.catalog.domain.Book;
import com.mgiandia.library.loans.domain.Item;
import com.mgiandia.library.persistence.BookRepository;
import com.mgiandia.library.representation.BookMapper;
import com.mgiandia.library.representation.BookRepresentation;

import com.mgiandia.library.representation.ItemMapper;
import com.mgiandia.library.representation.ItemRepresentation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path(BOOKS)
@RequestScoped
public class BookResource {

	@Context
	UriInfo uriInfo;
	
	@Inject 
	BookRepository bookRepository;
	
	@Inject
	BookMapper bookMapper;

	@Inject
	ItemMapper itemMapper;

	@GET
	@Path("{bookId:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response find(@PathParam("bookId") Integer bookId) {

		Book book = bookRepository.findById(bookId);
		if (book == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(bookMapper.toRepresentation(book)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<BookRepresentation> searchBookByTitle(@QueryParam("title") String title) {
		return bookMapper.toRepresentationList(bookRepository.search(title));
	}

	@GET
	@Path("{bookId:[0-9]*}/items/available")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<ItemRepresentation> getAvailableItems(@PathParam("bookId") Integer bookId) {

		Book book = bookRepository.findById(bookId);
		if (book == null) {
			throw new NotFoundException("Book not found");
		}

		List<Item> availableItems = book.getAvailableItems();

		return itemMapper.toRepresentationList(availableItems);
	}

}
