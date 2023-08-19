package amadeus.caseStudy.business.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import amadeus.caseStudy.entities.concretes.Flight;

public interface FlightService {
	List<Flight> getAll();
	Optional<Flight> GetById(int id);
	void Add(Flight flight);
	void SaveAll(List<Flight> flights);
	void Delete(int id);
	void Update(int id,Flight flight);
	List<Flight> searchFlights(String departure, String arrival, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
	List<Flight>sendApiRequest(String url);
}
