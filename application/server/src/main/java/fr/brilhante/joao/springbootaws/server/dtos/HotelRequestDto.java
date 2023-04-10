package fr.brilhante.joao.springbootaws.server.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelRequestDto(
	@NotNull
	@NotBlank
	String name,

	@NotNull
	@Min(0)
	@Max(5)
	Integer stars,

	@NotNull
	@NotBlank
	String address,

	@NotNull
	@NotBlank
	String city,

	@NotNull
	@NotBlank
	String country
) {
}
