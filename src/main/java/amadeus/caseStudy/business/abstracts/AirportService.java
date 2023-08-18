package amadeus.caseStudy.business.abstracts;

import java.util.List;
import java.util.Optional;

import amadeus.caseStudy.entities.concretes.Airport;
import amadeus.caseStudy.entities.concretes.Flight;

public interface AirportService  {
	List<Airport> getAll();
	Optional<Airport> GetById(int id);
	void Add(Airport airport);
	void SaveAll(List<Airport> airports);
	void Delete(int id);
	void Update(int id,Airport airport1);
	List<Flight> GetArrivingFlights(int airportId);
	List<Flight> GetDepartingFlights(int airportId);

}
