package fr.brilhante.joao.springbootaws.server.exceptions;

public class HotelAlreadyExistsException extends ResourceAlreadyExistsException {

	public HotelAlreadyExistsException(String name) {
		super(String.format("A hotel already exists with the name '%s'.", name));
	}

}
