package fr.brilhante.joao.springbootaws.server.controllers;

import fr.brilhante.joao.springbootaws.server.dtos.HotelRequestDto;
import fr.brilhante.joao.springbootaws.server.dtos.HotelResponseDto;
import fr.brilhante.joao.springbootaws.server.services.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	@GetMapping
	public Page<HotelResponseDto> findHotels(Pageable pageable) {
		return hotelService.findHotels(pageable);
	}

	@PostMapping
	public HotelResponseDto createHotel(@Valid @RequestBody HotelRequestDto hotelDto) {
		return hotelService.createHotel(hotelDto);
	}

	@GetMapping("{id}")
	public HotelResponseDto findHotel(@PathVariable UUID id) {
		return hotelService.findHotel(id);
	}

	@PutMapping("{id}")
	public HotelResponseDto updateHotel(@PathVariable UUID id, @Valid @RequestBody HotelRequestDto hotelDto) {
		return hotelService.updateHotel(id, hotelDto);
	}

	@DeleteMapping("{id}")
	public void deleteHotel(@PathVariable UUID id) {
		hotelService.deleteHotel(id);
	}

}
