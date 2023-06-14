package PartTwo.DispatcherServlet;
/*
Запуск текущего сервлета исходя из моих настроек:
http://localhost:8080/dispatcher-full-redirect
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher-full-redirect")
public class FullRedirect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/flights-demo-include-redirect");
    }
}
