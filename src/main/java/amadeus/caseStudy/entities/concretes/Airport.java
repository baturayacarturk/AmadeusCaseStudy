package amadeus.caseStudy.entities.concretes;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="airports")


public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="airport_id")
    private int airportId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="city")
    private String city;


    @JsonIgnore
    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.LAZY)
    private List<Flight> departingFlights;
    @JsonIgnore
    @OneToMany(mappedBy = "arrivalAirport", fetch = FetchType.LAZY)
    private List<Flight> arrivingFlights;

}
