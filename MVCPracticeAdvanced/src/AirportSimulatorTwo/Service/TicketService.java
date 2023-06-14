package AirportSimulatorTwo.Service;

import AirportSimulatorTwo.DAO.TicketDao;
import AirportSimulatorTwo.DTO.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private TicketService(){
    }
    public static TicketService getInstance(){
        return INSTANCE;
    }
    private final TicketDao ticketDao = TicketDao.getInstance();
    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).
                stream().
                map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlight_id(),
                        ticket.getSeat_no()
                )).
                collect(Collectors.toList());
    }
}
