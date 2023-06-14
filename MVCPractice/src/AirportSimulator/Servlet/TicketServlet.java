package AirportSimulator.Servlet;

import AirportSimulator.Service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets-by-id")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                    throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long flightId = Long.valueOf(req.getParameter("flightId"));

        try(PrintWriter writerToScreen = resp.getWriter()){
            writerToScreen.write("<h1>Купленные билеты :</h1>");
            writerToScreen.write("<ul>");
                ticketService.findAllByFlightId(flightId).forEach(ticketDto -> writerToScreen.write("""
                           <li>
                               %s - %s - %s
                           </li>     
                           """.formatted(ticketDto.getId(),ticketDto.getFlight_id(),ticketDto.getSeat_no())
                ));
            writerToScreen.write("</ul>");
        }
    }
}
