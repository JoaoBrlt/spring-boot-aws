package fr.brilhante.joao.springbootaws.server.services;

import fr.brilhante.joao.springbootaws.server.dtos.HotelRequestDto;
import fr.brilhante.joao.springbootaws.server.dtos.HotelResponseDto;
import fr.brilhante.joao.springbootaws.server.entities.Hotel;
import fr.brilhante.joao.springbootaws.server.exceptions.HotelAlreadyExistsException;
import fr.brilhante.joao.springbootaws.server.exceptions.HotelNotFoundException;
import fr.brilhante.joao.springbootaws.server.mappers.HotelMapper;
import fr.brilhante.joao.springbootaws.server.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	private final HotelMapper hotelMapper;

	public Page<HotelResponseDto> findHotels(Pageable pageable) {
		return hotelRepository.findAll(pageable).map(hotelMapper::toDto);
	}

	public HotelResponseDto findHotel(UUID id) {
		return hotelRepository
			.findById(id)
			.map(hotelMapper::toDto)
			.orElseThrow(() -> new HotelNotFoundException(id));
	}

	public HotelResponseDto createHotel(HotelRequestDto hotelDto) {
		if (hotelRepository.existsByName(hotelDto.name())) {
			throw new HotelAlreadyExistsException(hotelDto.name());
		}
		Hotel hotel = hotelMapper.toEntity(hotelDto);
		hotelRepository.save(hotel);
		return hotelMapper.toDto(hotel);
	}

	public HotelResponseDto updateHotel(UUID id, HotelRequestDto hotelDto) {
		Optional<Hotel> maybeHotel = hotelRepository.findById(id);
		if (maybeHotel.isEmpty()) {
			throw new HotelNotFoundException(id);
		}
		Hotel hotel = maybeHotel.get();
		hotelMapper.updateEntity(hotel, hotelDto);
		hotelRepository.save(hotel);
		return hotelMapper.toDto(hotel);
	}

	public void deleteHotel(UUID id) {
		Optional<Hotel> maybeHotel = hotelRepository.findById(id);
		if (maybeHotel.isEmpty()) {
			throw new HotelNotFoundException(id);
		}
		hotelRepository.deleteById(id);
	}

}
