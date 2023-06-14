/*
1. Создали папку lib и пометили её как Library
2. Разместили в ней servlet-api.jar
3. В разделе Project structure добавили:
   - Modules -> Web;
   - Artifacts -> Web exploded;

4. TomCat настроен на: http://localhost:8080/
*/
package FirstServlet;
/*
В данном примере мы используем программу
POSTMAN для формирования POST запросов.
Главное в самой программе Postman выбрать
правильно тип запроса - POST и отправить
текст ROW, например:
Hello
From
Postman!!!
*/
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.stream.Stream;

/*
Аннотация показывающая какую страницу
отобразить при обращении к данному сервлету.
*/
@WebServlet("/third")
public class ThirdServletPostTxt extends HttpServlet {
    /* Вариант метода GET запроса */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                        throws ServletException, IOException {
        /* Запросим все хедеры */
        Enumeration<String> ourHeadersName= req.getHeaderNames();
        while (ourHeadersName.hasMoreElements()){
            String thisHeader = ourHeadersName.nextElement();
            System.out.println(req.getHeader(thisHeader));
        }

        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter writeToWebPage = resp.getWriter()){
            writeToWebPage.write("<h1>Third Servlet is here!</h1>");
        }
    }
    /* Метод занимающийся обработкой POST запроса */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                                        throws ServletException, IOException {
        /*
        Тут мы получаем текст из Postman и выводим его в консоль.
        В данном случае мы как будто считали некий файл с нашего
        диска (хотя получили его во входящем потоке из браузера),
        поэтому используем *.getReader и полученный текст помещается
        в буфер чтения из которого мы его считываем, как поток,
        построчно */
        try (BufferedReader txtReader = req.getReader();
             Stream<String> readLines = txtReader.lines()){
            readLines.forEach(System.out::println);
        }
        /* Этот текст сервлет отобразит в Postman - e и браузере */
        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter writeToWebPage = resp.getWriter()){
            writeToWebPage.write("<h1>Second Servlet response after POST request with TXT!</h1>");
        }
    }

    /* Метод для 'сворачивания' - undeploy нашего сервлета */
    @Override
    public void destroy() {
        super.destroy();
    }
    /* Метод для инициализации нашего сервлета */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}