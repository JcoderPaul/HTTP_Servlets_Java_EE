package PartThree.JspRedirect;
/*
Запускать через браузер по адресу (у меня настроено на:)
http://localhost:8080/content-second
*/
import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/content-second")
public class ContentServletSecond extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        В данном случае мы прописали только название jsp страницы, мы использовали
        наш JspWritePathHelper и его метод *.getJspPath(), которые задают к имени
        странички 'префикс' - /WEB-INF/jsp/ и 'постфикс' - .jsp. И это логично, т.к.
        путь к директории и расширение файла сохраняются, для всех jsp в заданной
        папке.
        */
        String jsp_path = JspPathHelper.getJspPath("content");
        System.out.println(jsp_path);
        req.getRequestDispatcher(jsp_path).forward(req,resp);
    }
}
