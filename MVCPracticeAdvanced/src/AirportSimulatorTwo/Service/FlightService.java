package AirportSimulatorTwo.Service;

import AirportSimulatorTwo.DAO.FlightDao;
import AirportSimulatorTwo.DTO.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private static final FlightService INSTANCE = new FlightService();
    private FlightService() {
    }
    public static FlightService getInstance() {
        return INSTANCE;
    }
    private final FlightDao flightDao = FlightDao.getInstance();
    public List<FlightDto> findAll(){
        return flightDao.
                findAll().
                stream().
                map(flight -> new FlightDto(
                        flight.getId(),
                        """
                        %s - %s - %s
                        """.formatted(flight.getDeparture_airport_code(),
                                      flight.getArrival_airport_code(),
                                      flight.getStatus())
                )).
                collect(Collectors.toList());
    }
}
