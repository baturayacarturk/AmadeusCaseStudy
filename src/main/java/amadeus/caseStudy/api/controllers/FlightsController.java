package amadeus.caseStudy.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amadeus.caseStudy.business.abstracts.AirportService;
import amadeus.caseStudy.business.abstracts.FlightService;
import amadeus.caseStudy.entities.concretes.Flight;

@RestController
@RequestMapping("api/flights")
public class FlightsController {
	
	private FlightService flightService;
	private AirportService airportService;
	
	@Autowired
	public FlightsController(FlightService flightService,AirportService airportService) {
		super();
		this.flightService = flightService;
		this.airportService=airportService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Flight>> getAll(){
		return ResponseEntity.ok(this.flightService.getAll());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Flight>getById(@RequestParam int id){
		Optional<Flight> optionalFlight = this.flightService.GetById(id);
		return ResponseEntity.ok(optionalFlight.get());
	}
	@PostMapping("/addFlight")
	public ResponseEntity AddFlight(Flight flight) {
		this.flightService.Add(flight);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/addFlights")
	public ResponseEntity SaveFlightList(List<Flight> flight) {
		this.flightService.SaveAll(flight);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/deleteById")
	public ResponseEntity DeleteFlightById(@RequestParam int id) {
		this.flightService.Delete(id);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/update")
	public ResponseEntity UpdateFlight(@RequestParam int id, @RequestBody Flight flight) {
		this.flightService.Update(id,flight);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/airport/{airportId}/arrivingFlights")
	public ResponseEntity<List<Flight>>getArrivingFlights(@PathVariable int airportId){
		return ResponseEntity.ok().body(this.airportService.GetArrivingFlights(airportId));
	}
	@GetMapping("/airport/{airportId}/departingFlights")
	public ResponseEntity<List<Flight>>getDepartingFlights(@PathVariable int airportId){
		return ResponseEntity.ok().body(this.airportService.GetDepartingFlights(airportId));
	}
	
	
	
}
