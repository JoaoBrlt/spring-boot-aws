package fr.brilhante.joao.springbootaws.server.dtos;

import java.util.UUID;

public record HotelResponseDto(
	UUID id,
	String name,
	Integer stars,
	String address,
	String city,
	String country
) {
}
