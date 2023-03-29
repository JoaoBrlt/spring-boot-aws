package fr.brilhante.joao.springbootaws.server.exceptions;

import java.util.UUID;

public class HotelNotFoundException extends ResourceNotFoundException {

	public HotelNotFoundException(UUID id) {
		super(String.format("No hotel was found with the identifier '%s'.", id));
	}

}
