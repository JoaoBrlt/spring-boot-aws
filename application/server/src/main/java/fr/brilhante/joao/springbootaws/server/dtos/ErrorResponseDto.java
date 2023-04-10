package fr.brilhante.joao.springbootaws.server.dtos;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public record ErrorResponseDto(
	int status,
	String title,
	String message,
	List<String> errors,
	Instant date
) {

	public ErrorResponseDto(int status, String title, String message, List<String> errors) {
		this(status, title, message, errors, Instant.now());
	}

	public ErrorResponseDto(int status, String title, String message) {
		this(status, title, message, Collections.emptyList());
	}

}
