package AirportSimulator.DAO;

import AirportSimulator.Entity.Ticket;
import AirportSimulator.Util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();
    private TicketDao() {
    }
    public static TicketDao getInstance() {
        return INSTANCE;
    }
    private static final String FIND_ALL_BY_FLIGHT_ID = """
            SELECT *
            FROM ticket
            WHERE flight_id = ?
            """;

    public List<Ticket> findAllByFlightId(Long flightId){
        try(Connection myConnection = ConnectionManager.getBaseConnection();
            PreparedStatement myPrepStatement =
                    myConnection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)) {
            myPrepStatement.setObject(1,flightId);

            ResultSet resultOfQuery = myPrepStatement.executeQuery();
            List<Ticket> ticketsFromQuery = new ArrayList<>();
            while (resultOfQuery.next()){
                ticketsFromQuery.add(buildTicket(resultOfQuery));
            }
            return ticketsFromQuery;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    private Ticket buildTicket(ResultSet resultOfQuery) throws SQLException {
        return new Ticket(
                resultOfQuery.getObject("id", Long.class),
                resultOfQuery.getObject("passenger_no", String.class),
                resultOfQuery.getObject("passenger_name", String.class),
                resultOfQuery.getObject("flight_id", Long.class),
                resultOfQuery.getObject("seat_no", String.class),
                resultOfQuery.getObject("cost", BigDecimal.class)
        );
    }
}
