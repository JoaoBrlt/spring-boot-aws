package fr.brilhante.joao.springbootaws.server.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
