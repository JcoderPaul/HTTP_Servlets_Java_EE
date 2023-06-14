package AirportSimulatorTwo.Entity;

import AirportSimulatorTwo.Entity.EntityEnum.FlightStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Long id;
    private String flight_no;
    private LocalDateTime departure_date;
    private String departure_airport_code;
    private LocalDateTime arrival_date;
    private String arrival_airport_code;
    private Integer aircraft_id;
    private FlightStatus status;

    public Flight() {
    }

    public Flight(Long id, String flight_no, LocalDateTime departure_date,
                  String departure_airport_code, LocalDateTime arrival_date,
                  String arrival_airport_code, Integer aircraft_id,
                  FlightStatus status) {
        this.id = id;
        this.flight_no = flight_no;
        this.departure_date = departure_date;
        this.departure_airport_code = departure_airport_code;
        this.arrival_date = arrival_date;
        this.arrival_airport_code = arrival_airport_code;
        this.aircraft_id = aircraft_id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public LocalDateTime getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDateTime departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_airport_code() {
        return departure_airport_code;
    }

    public void setDeparture_airport_code(String departure_airport_code) {
        this.departure_airport_code = departure_airport_code;
    }

    public LocalDateTime getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(LocalDateTime arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_airport_code() {
        return arrival_airport_code;
    }

    public void setArrival_airport_code(String arrival_airport_code) {
        this.arrival_airport_code = arrival_airport_code;
    }

    public Integer getAircraft_id() {
        return aircraft_id;
    }

    public void setAircraft_id(Integer aircraft_id) {
        this.aircraft_id = aircraft_id;
    }
    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(flight_no, flight.flight_no) &&
                Objects.equals(departure_date, flight.departure_date) &&
                Objects.equals(departure_airport_code, flight.departure_airport_code) &&
                Objects.equals(arrival_date, flight.arrival_date) &&
                Objects.equals(arrival_airport_code, flight.arrival_airport_code) &&
                Objects.equals(aircraft_id, flight.aircraft_id) &&
                status == flight.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                flight_no,
                departure_date,
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flight_no='" + flight_no + '\'' +
                ", departure_date=" + departure_date +
                ", departure_airport_code='" + departure_airport_code + '\'' +
                ", arrival_date=" + arrival_date +
                ", arrival_airport_code='" + arrival_airport_code + '\'' +
                ", aircraft_id=" + aircraft_id +
                ", status=" + status +
                '}';
    }
}
