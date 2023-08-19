package amadeus.caseStudy.dataAccess.abstracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import amadeus.caseStudy.entities.concretes.Flight;
import io.micrometer.common.lang.Nullable;

@Repository
public interface FlightDao extends JpaRepository<Flight,Integer>{

	@Query("FROM Flight f WHERE f.departureAirport.city = :departure AND f.arrivalAirport.city = :arrival AND f.departureDateTime >= :departureDateTime"
	        + " AND (cast(:returnDateTime as date) IS NULL OR f.returnDateTime <= :returnDateTime)")
	List<Flight> findFlightsByCriteria(
	        @Param("departure") String departure,
	        @Param("arrival") String arrival,
	        @Param("departureDateTime") LocalDateTime departureDateTime,
	        @Param("returnDateTime") LocalDateTime returnDateTime);
}
