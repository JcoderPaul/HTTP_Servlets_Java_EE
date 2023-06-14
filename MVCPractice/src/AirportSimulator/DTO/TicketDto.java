package AirportSimulator.DTO;

import java.util.Objects;

public class TicketDto {
    private final Long id;
    private final Long flight_id;
    private final String seat_no;

    public TicketDto(Long id, Long flight_id, String seat_no) {
        this.id = id;
        this.flight_id = flight_id;
        this.seat_no = seat_no;
    }

    public Long getId() {
        return id;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public String getSeat_no() {
        return seat_no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(id, ticketDto.id) &&
                Objects.equals(flight_id, ticketDto.flight_id) &&
                Objects.equals(seat_no, ticketDto.seat_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight_id, seat_no);
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", flight_id=" + flight_id +
                ", seat_no='" + seat_no + '\'' +
                '}';
    }
}
