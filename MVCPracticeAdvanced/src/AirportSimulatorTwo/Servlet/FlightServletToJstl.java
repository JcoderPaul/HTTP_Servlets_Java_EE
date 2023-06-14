package AirportSimulatorTwo.Servlet;
/*
Данный сервлет "почти" повторяет работу FlightServlet сервлета
с разницей в том, что тут происходит перенаправление запроса
на WEB_INF/Jsp/flights.jsp, которая выполнит всю логику вывода
информации, как это было сделано в FlightServlet.
Для проверки использовать: http://localhost:8080/flights
*/
import AirportSimulatorTwo.Service.FlightService;
import AirportSimulatorTwo.Util.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/flights")
public class FlightServletToJstl extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                    throws ServletException, IOException {

        /*
        Задаем и передаем в req атрибут "flights" с массивом данных
        'список перелетов', далее задаем путь на наш *.jsp файл в
        директории WEB_INF/JstlDemo/getFlights.jsp и методом *.forward()
        перенаправляем на него наши (req, resp) (можно было и короче, но
        две строки кода введены для наглядности в консоли).
        */
        req.setAttribute("flights", flightService.findAll());

        String jspPath = JspPathHelper.getJspPath("flights");
        System.out.println(jspPath);
        req.getRequestDispatcher(jspPath).forward(req,resp);

    }
}
