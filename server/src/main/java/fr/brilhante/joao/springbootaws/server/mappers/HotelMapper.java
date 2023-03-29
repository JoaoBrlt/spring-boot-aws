package fr.brilhante.joao.springbootaws.server.mappers;

import fr.brilhante.joao.springbootaws.server.dtos.HotelRequestDto;
import fr.brilhante.joao.springbootaws.server.dtos.HotelResponseDto;
import fr.brilhante.joao.springbootaws.server.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface HotelMapper {

	Hotel toEntity(HotelRequestDto hotelDto);

	HotelResponseDto toDto(Hotel hotel);

	void updateEntity(@MappingTarget Hotel hotel, HotelRequestDto hotelDto);

}
