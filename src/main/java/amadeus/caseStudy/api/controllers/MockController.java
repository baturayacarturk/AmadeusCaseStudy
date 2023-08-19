package amadeus.caseStudy.api.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amadeus.caseStudy.entities.concretes.Airport;
import amadeus.caseStudy.entities.concretes.Flight;

@RestController
@RequestMapping("api/mock")

public class MockController {

	@PostMapping("/todaysManifest")
	public List<Flight> sendTodaysManifest(){
		List<Flight> flightList = new ArrayList<>();

		Airport istanbulAirport = new Airport();
		istanbulAirport.setAirportId(1);
		istanbulAirport.setName("Istanbul Airport");
		istanbulAirport.setCity("Istanbul");

		Airport ankaraAirport = new Airport();
		ankaraAirport.setAirportId(2);
		ankaraAirport.setName("Ankara Esenboğa Airport");
		ankaraAirport.setCity("Ankara");

		Flight flight1 = new Flight();
		flight1.setDepartureAirport(istanbulAirport);
		flight1.setArrivalAirport(ankaraAirport);
		flight1.setDepartureDateTime(LocalDateTime.now());
		flight1.setPrice(150.0);
		flightList.add(flight1);

		Flight flight2 = new Flight();
		flight2.setFlightId(2);
		flight2.setDepartureAirport(istanbulAirport);
		flight2.setArrivalAirport(ankaraAirport);
		flight2.setDepartureDateTime(LocalDateTime.now().plusHours(2));
		flight2.setPrice(200.0);
		flightList.add(flight2);

		Flight flight3 = new Flight();
		flight3.setFlightId(3);
		flight3.setDepartureAirport(ankaraAirport);
		flight3.setArrivalAirport(istanbulAirport);
		flight3.setDepartureDateTime(LocalDateTime.now().plusHours(4));
		flight3.setPrice(180.0);
		flightList.add(flight3);
		
		return flightList;

	}
}
