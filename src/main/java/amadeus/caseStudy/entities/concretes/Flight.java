package amadeus.caseStudy.entities.concretes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="flights")

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_id")
    private int flightId;
    
    @ManyToOne()
    @JoinColumn(name = "departure_airport_id")
    
    private Airport departureAirport;
    @ManyToOne()
    @JoinColumn(name = "arrival_airport_id")

    private Airport arrivalAirport;

    @Column(name="departure_date_time")
    private LocalDateTime departureDateTime;
    
    @Column(name="return_date_time",nullable=true)
    private LocalDateTime returnDateTime;
    
    @Column(name="price")
    private Double price;
   
}
