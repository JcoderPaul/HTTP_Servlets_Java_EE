package PartTwo.DispatcherServlet;

/* Сервлет для демонстрации редиректа в сервлетах */

import AirportSimulator.Service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights-demo-include-redirect")
public class FlightDemoRedirectServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                    throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

            /*
            В отличие от FlightServlet, тут мы убрали блок try-with-resources,
            а значит не закрыли исходящий поток и не прекратили запись в него,
            что позволил нам продолжить запись в исходящий поток в IncludeRedirect
            сервлете.

            Если же мы оставили блок try-with-resources, то клиент уже получил бы
            response, т.к. исходящий поток был бы уже закрыт, хотя мы бы еще
            находились на стороне сервера. Браузер бы все равно отразил бы
            "список перелетов".

            Естественно данные рассуждения немного не работают если в сервлете
            IncludeRedirect мы пропишем некую логику, обращенную к исходящему
            потоку еще до применения метода *.include()
            */
            PrintWriter respPrintWriter = resp.getWriter();
            respPrintWriter.write("<h1>Список перелетов (из /flights-demo-include-redirect):</h1>");
            respPrintWriter.write("<ul>");
            flightService.findAll().forEach(flightDto -> {
                respPrintWriter.write("""
                        <li>
                            <a href="tickets-by-id?flightId=%d">%s</a> 
                        </li>
                        """.formatted(flightDto.getId(), flightDto.getDescription()));
            });
            respPrintWriter.write("</ul>");
    }
}
