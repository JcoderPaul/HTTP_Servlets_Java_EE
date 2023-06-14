package PartFour.JSTLServletDemo;
/*
Данный сервлет "почти" повторяет работу TicketServlet сервлета
с разницей в том, что тут происходит перенаправление запроса
на WEB_INF/JstlDemo/getTickets.jsp, которая выполнит всю логику вывода
информации, как это было сделано в TicketServlet.
Для проверки: http://localhost:8080/jstl-tickets-by-id?flightId=4
*/
import AirportSimulator.DTO.TicketDto;
import AirportSimulator.Service.TicketService;
import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/jstl-tickets-by-id")
public class JstlTicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                    throws ServletException, IOException {

        Long flightId = Long.valueOf(req.getParameter("flightId"));
        /*
        Хотя можно было напрямую поместить объект полученный из ticketService
        однако при пошаговом прогоне программы становится видно, что в него
        помещено и какими именами обладают поля, чтобы их применить в
        getTickets.jsp с использованием JSTL, например ${tickets.seat_no} -
        извлечь из списка 'tickets' все данные с полями 'seat_no'
        */
        List<TicketDto> allTicketsById = ticketService.findAllByFlightId(flightId);
        req.setAttribute("tickets", allTicketsById);

        req.getRequestDispatcher(JspPathHelper.getJstlDempPath("getTickets")).forward(req,resp);
    }
}
