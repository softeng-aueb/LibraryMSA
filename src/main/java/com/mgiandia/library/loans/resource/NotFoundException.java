package com.mgiandia.library.loans.resource;

import com.mgiandia.library.loans.LibraryException;

public class NotFoundException extends LibraryException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
