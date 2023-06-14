package PartTwo.DispatcherServlet;
/*
В случае применении Forward перенаправления, клиент не видит
что происходи перенаправление на другой сервлет. Т.е. сначала
клиент делает запрос на сервер к /dispatcher, тот в свою
очередь, перенаправляет на FlightServlet и уже данный сервлет,
формирует ответ серверу. И сервер возвращает response клиенту.

Запуск текущего сервлета исходя из моих настроек:
http://localhost:8080/dispatcher-forward
*/
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher-forward")
public class ForwardRedirect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        Сервлет контекст знает про все сервлеты и их map-инги, поэтому мы можем
        применить вариант перенаправления с его участием:
        getServletContext().getRequestDispatcher().forward("req","resp");
        Либо использовать метод примененный ниже.
        */
        RequestDispatcher myRequestRedirect = req.getRequestDispatcher("/flights-demo-include-redirect");
        /*
        Запрос 'req' пришедший от клиента на текущий сервлет мы перенаправляем.
        До тех пор, пока не был применен метод *.forward() мы можем добавить
        некие необходимые нам атрибуты в Request:
        req.setAttribute("some param","some param");
        */
        myRequestRedirect.forward(req,resp);
        /*
        !!! В данной ситуации мы отправляем запрос другому сервлету
        и далее продолжает работу логика именно того сервлета, на
        который было перенаправление, т.е. в текущий сервлет мы уже
        не возвращаемся см. DOC/PartTwo.DispatcherServlet.jpg !!!
        */
    }
}
