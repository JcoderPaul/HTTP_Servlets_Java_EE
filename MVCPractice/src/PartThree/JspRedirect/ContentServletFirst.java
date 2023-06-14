package PartThree.JspRedirect;
/*
Запускать через браузер по адресу (у меня настроено на:)
http://localhost:8080/content-first
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/content-first")
public class ContentServletFirst extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        В данном случае мы прописали путь руками, хотя можно создать
        утилитный (вспомогательный) класс, для упрощения написания пути
        к закрытым в WEB-INF/jsp/ страницам. Смотреть ContentServletSecond.
        */
        req.getRequestDispatcher("/WEB-INF/jsp/content.jsp").forward(req,resp);
    }
}
