package amadeus.caseStudy.core.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amadeus.caseStudy.core.exceptions.BusinessException;
import amadeus.caseStudy.dataAccess.abstracts.AirportDao;
import amadeus.caseStudy.entities.concretes.Airport;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class AirportRules implements AirportRulesService  {
	
	private AirportDao airportDao;
	
	@Autowired
	public AirportRules(AirportDao airportDao) {
		super();
		this.airportDao=airportDao;
	}
	
	public void AirportMustExists(int id) {
		Optional<Airport> airport = airportDao.findById(id);
		if(airport.isEmpty()) {
			throw new BusinessException("Airport not found with id: "+id);
		}
	}

}
