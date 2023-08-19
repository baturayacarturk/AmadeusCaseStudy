package amadeus.caseStudy.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amadeus.caseStudy.business.abstracts.FlightService;
import amadeus.caseStudy.dataAccess.abstracts.FlightDao;
import amadeus.caseStudy.entities.concretes.Flight;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class FlightManager implements FlightService{

	private FlightDao flightDao;
	
	@Autowired
	public FlightManager(FlightDao flightDao) {
		super();
		this.flightDao=flightDao;
	}
	@Override
	public List<Flight> getAll() {
		// TODO Auto-generated method stub
		return this.flightDao.findAll();
	}
	@Override
	public void Add(Flight flight) {
		this.flightDao.save(flight);	
	}
	@Override
	public void SaveAll(List<Flight> flights) {
		this.flightDao.saveAll(flights);
	}
	@Override
	public void Delete(int id) {
		this.flightDao.deleteById(id);
	}
	@Override
	public void Update(int id ,Flight flight) {
		Optional<Flight> existingFlight = this.flightDao.findById(id);
		Flight flightToUpdate = existingFlight.get();
		flightToUpdate=flight;
		flightDao.save(flightToUpdate);
		
	}
	@Override
	public Optional<Flight>GetById(int id) {
		return this.flightDao.findById(id);
	}
	@Override
	public List<Flight> searchFlights(String departure, String arrival, LocalDateTime departureDateTime,
			LocalDateTime returnDateTime) {
		// TODO Auto-generated method stub
		return this.flightDao.findFlightsByCriteria(departure, arrival, departureDateTime, returnDateTime);
	}
	

}
