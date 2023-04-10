package fr.brilhante.joao.springbootaws.server.controllers;

import fr.brilhante.joao.springbootaws.server.dtos.HotelRequestDto;
import fr.brilhante.joao.springbootaws.server.dtos.HotelResponseDto;
import fr.brilhante.joao.springbootaws.server.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	@GetMapping
	@Operation(summary = "Find all hotels")
	@ApiResponse(responseCode = "200", description = "The request was successfully completed")
	public Page<HotelResponseDto> findHotels(Pageable pageable) {
		return hotelService.findHotels(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a hotel")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "The hotel was successfully created"),
		@ApiResponse(responseCode = "400", description = "The hotel is invalid"),
		@ApiResponse(responseCode = "409", description = "The hotel already exists")
	})
	public HotelResponseDto createHotel(@Valid @RequestBody HotelRequestDto hotelDto) {
		return hotelService.createHotel(hotelDto);
	}

	@GetMapping("{id}")
	@Operation(summary = "Find a hotel by identifier")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "The hotel was found"),
		@ApiResponse(responseCode = "404", description = "The hotel was not found"),
	})
	public HotelResponseDto findHotel(@PathVariable UUID id) {
		return hotelService.findHotel(id);
	}

	@PutMapping("{id}")
	@Operation(summary = "Update a hotel by identifier")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "The hotel was successfully updated"),
		@ApiResponse(responseCode = "400", description = "The hotel is invalid"),
		@ApiResponse(responseCode = "404", description = "The hotel was not found")
	})
	public HotelResponseDto updateHotel(@PathVariable UUID id, @Valid @RequestBody HotelRequestDto hotelDto) {
		return hotelService.updateHotel(id, hotelDto);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete a hotel by identifier")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "The hotel was deleted"),
		@ApiResponse(responseCode = "404", description = "The hotel was not found"),
	})
	public void deleteHotel(@PathVariable UUID id) {
		hotelService.deleteHotel(id);
	}

}
