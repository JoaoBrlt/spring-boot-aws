package fr.brilhante.joao.springbootaws.server.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false)
	@Setter(value = AccessLevel.NONE)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer stars;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String country;

	public Hotel(String name, int stars, String address, String city, String country) {
		this.name = name;
		this.stars = stars;
		this.address = address;
		this.city = city;
		this.country = country;
	}

}
