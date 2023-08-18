package amadeus.caseStudy.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amadeus.caseStudy.entities.concretes.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight,Integer>{

}
