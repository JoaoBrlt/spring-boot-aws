package fr.brilhante.joao.springbootaws.server.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record HotelRequestDto(
	@NotNull
	String name,

	@NotNull
	@Min(0)
	@Max(5)
	Integer stars,

	@NotNull
	String address,

	@NotNull
	String city,

	@NotNull
	String country
) {
}
