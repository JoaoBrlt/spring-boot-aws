package fr.brilhante.joao.springbootaws.server.repositories;

import fr.brilhante.joao.springbootaws.server.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {

	boolean existsByName(String name);

}
