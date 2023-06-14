package PartFour.JSTLServletDemo;
/*
Данный сервлет "почти" повторяет работу FlightServlet сервлета
с разницей в том, что тут происходит перенаправление запроса
на WEB_INF/JstlDemo/getFlights.jsp, которая выполнит всю логику вывода
информации, как это было сделано в FlightServlet.
Для проверки использовать: http://localhost:8080/jstl-flight
*/
import AirportSimulator.Service.FlightService;
import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/jstl-flight")
public class JstlFlightServlet extends HttpServlet {
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

        String jstlDemoPath = JspPathHelper.getJstlDempPath("getFlights");
        System.out.println(jstlDemoPath);
        req.getRequestDispatcher(jstlDemoPath).forward(req,resp);

    }
}
