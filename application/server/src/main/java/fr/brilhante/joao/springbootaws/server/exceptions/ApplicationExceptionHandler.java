package fr.brilhante.joao.springbootaws.server.exceptions;

import fr.brilhante.joao.springbootaws.server.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleResourceNotFoundException(ResourceNotFoundException exception) {
		return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), "Resource Not Found", exception.getMessage());
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponseDto handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
		return new ErrorResponseDto(HttpStatus.CONFLICT.value(), "Resource Already Exists", exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult()
			.getFieldErrors()
			.stream()
			.map((error -> error.getField() + " " + error.getDefaultMessage()))
			.toList();

		return new ErrorResponseDto(
			HttpStatus.BAD_REQUEST.value(),
			"Invalid Argument",
			"The provided argument is invalid.",
			errors
		);
	}

}
