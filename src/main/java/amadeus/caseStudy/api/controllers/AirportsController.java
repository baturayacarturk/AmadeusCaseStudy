package amadeus.caseStudy.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amadeus.caseStudy.business.abstracts.AirportService;

import amadeus.caseStudy.entities.concretes.Airport;

@RestController
@RequestMapping("api/airports")
public class AirportsController {
	private AirportService airportService;
	
	@Autowired
	public AirportsController(AirportService airportService) {
		super();
		this.airportService = airportService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Airport>> getAll(){
		return ResponseEntity.ok(this.airportService.getAll());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Airport>getById(@RequestParam int id){
		Optional<Airport> optionalAirport = this.airportService.GetById(id);
		return ResponseEntity.ok(optionalAirport.get());
	}
	@PostMapping("/addAirport")
	public ResponseEntity AddAirport(Airport airport) {
		this.airportService.Add(airport);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/addAirports")
	public ResponseEntity SaveFlightList(List<Airport> airport) {
		this.airportService.SaveAll(airport);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/deleteById")
	public ResponseEntity DeleteFlightById(@RequestParam int id) {
		this.airportService.Delete(id);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/update")
	public ResponseEntity UpdateAirport(@RequestParam int id, @RequestBody Airport airport) {
		this.airportService.Update(id,airport);
		return ResponseEntity.ok().build();
	}
	
}
