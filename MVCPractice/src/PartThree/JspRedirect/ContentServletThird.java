package PartThree.JspRedirect;
/*
Запускать через браузер по адресу (у меня настроено на:)
http://localhost:8080/content-jstl-demo?id=2&test=hello_from_jsp
С данного адреса произойдет прямая *.forward(req,resp)
переадресация на content.jsp из папки WEB-INF
*/
import AirportSimulator.DTO.FlightDto;
import AirportSimulator.Service.FlightService;
import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content-jstl-demo")
public class ContentServletThird extends HttpServlet {
    private final String jspPath = JspPathHelper.getJspPath("content");
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightDtoList = flightService.findAll();

        req.setAttribute("flights", flightDtoList);
        req.setAttribute("flightsSize", flightDtoList.size());

        req.getSession().setAttribute("flightsMap", flightDtoList.stream().
                collect(toMap(FlightDto::getId, FlightDto::getDescription)));

        req.getRequestDispatcher(jspPath).forward(req,resp);
    }
}
