package fr.brilhante.joao.springbootaws.server.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

}
