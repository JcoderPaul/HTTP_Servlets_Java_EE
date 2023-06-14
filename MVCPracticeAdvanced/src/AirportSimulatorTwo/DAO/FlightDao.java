package AirportSimulatorTwo.DAO;

import AirportSimulatorTwo.Entity.Flight;
import AirportSimulatorTwo.Entity.EntityEnum.FlightStatus;
import AirportSimulatorTwo.Util.ConnectionManager;

import java.sql.*;
import java.util.*;

public class FlightDao implements Dao<Long, Flight>{
    private static final FlightDao INSTANCE = new FlightDao();
    private FlightDao() {
    }
    public static FlightDao getInstance(){
        return INSTANCE;
    }
    private static final String FIND_ALL = """
            SELECT * 
            FROM flight
            """;
    @Override
    public List<Flight> findAll() {
        try(Connection myConnect = ConnectionManager.getBaseConnection();
            PreparedStatement myPrepStatement = myConnect.prepareStatement(FIND_ALL)){
            ResultSet resultOfQuery = myPrepStatement.executeQuery();
            List<Flight> flightsFromQuery = new ArrayList<>();
            while (resultOfQuery.next()){
                flightsFromQuery.add(buildFlight(resultOfQuery));
            }
            return flightsFromQuery;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    private Flight buildFlight(ResultSet flightsFromQuery) throws SQLException {
        return new Flight(
                flightsFromQuery.getObject("id", Long.class),
                flightsFromQuery.getObject("flight_no", String.class),
                flightsFromQuery.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                flightsFromQuery.getObject("departure_airport_code", String.class),
                flightsFromQuery.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                flightsFromQuery.getObject("arrival_airport_code", String.class),
                flightsFromQuery.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(flightsFromQuery.getObject("status", String.class))
        );
    }
}
