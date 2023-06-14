package PartTwo.DispatcherServlet;
/*
В случае применении Include перенаправления, клиент не видит
что происходи перенаправление на другой сервлет. Т.е. сначала
клиент делает запрос на сервер к /dispatcher, тот в свою
очередь, перенаправляет на FlightServlet. Теперь данный сервлет,
формирует ответ Dispatcher - у. Dispatcher возвращает результат
серверу. А сервер возвращает response клиенту см.
PartTwo.DispatcherServlet.jpg

Запуск текущего сервлета исходя из моих настроек:
http://localhost:8080/dispatcher-include
*/
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/dispatcher-include")
public class IncludeRedirect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher myRequestRedirect = req.getRequestDispatcher("/flights-demo-include-redirect");

        myRequestRedirect.include(req,resp);
        /* Устанавливаем хедеры, для благообразного отображения кириллицы в браузере */
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        /* Отправляем сообщение из текущего сервлета */
        PrintWriter respPrintWriterInDispatcher = resp.getWriter();
        respPrintWriterInDispatcher.write("<H1>Hello from Dispatcher-Include!!!</H1>");
        /*
        !!! В данной ситуации мы отправляем запрос другому сервлету,
        тот в свою очередь делает некую работу, а затем возвращает
        ответ Dispatcher сервлету, который может добавить еще некой
        логики и только затем вернуть ответ серверу. И наконец сервер
        возвращает ответ клиенту см. PartTwo.DispatcherServlet.jpg !!!
        */
    }
}
