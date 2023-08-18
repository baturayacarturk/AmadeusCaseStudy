package amadeus.caseStudy.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amadeus.caseStudy.entities.concretes.Airport;
@Repository
public interface AirportDao extends JpaRepository<Airport,Integer>{

}
