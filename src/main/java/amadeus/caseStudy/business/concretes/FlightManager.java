package amadeus.caseStudy.business.concretes;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import amadeus.caseStudy.business.abstracts.FlightService;
import amadeus.caseStudy.dataAccess.abstracts.FlightDao;
import amadeus.caseStudy.entities.concretes.Flight;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class FlightManager implements FlightService{

	private FlightDao flightDao;
	private RestTemplate restTemplate;
	
	@Autowired
	public FlightManager(FlightDao flightDao, RestTemplate restTemplate) {
		super();
		this.flightDao=flightDao;
		this.restTemplate=restTemplate;
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
		for (Flight flight : flights) {
	        flightDao.save(flight);
	    }
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
	@Override
	public List<Flight> sendApiRequest(String url) {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List<Flight>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Flight>>() {});
        List<Flight> flights = responseEntity.getBody();

			
			this.SaveAll(flights);
		    return flights;
		
	    
	}
	

}
