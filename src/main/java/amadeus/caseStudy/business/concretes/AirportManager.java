package amadeus.caseStudy.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import amadeus.caseStudy.business.abstracts.AirportService;
import amadeus.caseStudy.core.rules.AirportRules;
import amadeus.caseStudy.core.rules.AirportRulesService;
import amadeus.caseStudy.dataAccess.abstracts.AirportDao;

import amadeus.caseStudy.entities.concretes.Airport;
import amadeus.caseStudy.entities.concretes.Flight;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class AirportManager implements AirportService {

private AirportDao airportDao;

private AirportRulesService airportRules;
	
	@Autowired
	public AirportManager(AirportDao airportDao, AirportRulesService airportRules) {
		super();
		this.airportDao=airportDao;
		this.airportRules=airportRules;
	}
	public List<Airport> getAll() {
		// TODO Auto-generated method stub
		return this.airportDao.findAll();
	}
	@Override
	public void Add(Airport airport) {
		this.airportDao.save(airport);	
	}
	@Override
	public void SaveAll(List<Airport> airports) {
		this.airportDao.saveAll(airports);
	}
	@Override
	public void Delete(int id) {
		this.airportDao.deleteById(id);
	}
	@Override
	public void Update(int id ,Airport airport) {
		Optional<Airport> existingAirport = this.airportDao.findById(id);
		Airport airportToUpdate = existingAirport.get();
		airportToUpdate =airport;
		airportDao.save(airportToUpdate);
		
	}
	@Override
	public Optional<Airport>GetById(int id) {
		airportRules.AirportMustExists(id);
		return this.airportDao.findById(id);
	}
	@Override
	public List<Flight> GetArrivingFlights(int airportId) {
		// TODO Auto-generated method stub
		Airport airport = airportDao.findById(airportId).orElse(null);
		return airport.getArrivingFlights();
	}
	@Override
	public List<Flight> GetDepartingFlights(int airportId) {
		// TODO Auto-generated method stub
		Airport airport = airportDao.findById(airportId).orElse(null);
		return airport.getDepartingFlights();
	}
	

}
