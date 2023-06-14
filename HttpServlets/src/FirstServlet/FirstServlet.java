package FirstServlet;
/*
1. Создали папку lib и пометили её как Library
2. Разместили в ней servlet-api.jar
3. В разделе Project structure добавили:
   - Modules -> Web;
   - Artifacts -> Web exploded;

4. TomCat настроен на: http://localhost:8080/
*/
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
При запуске сервлета работа проходит через три ступени:
1. Запускается метод *.init();
2. Далее метод *.service() и внутри него перебираются
   и идут в работу методы GET, POST и т.д. При повторном
   обращении к сервлету без его свертывания, метод
   *.init() пропускается и сразу начинает работу метод
   *.service() и его содержимое;
3. В случае перезагрузки или выгрузки сервлета запускается
   метод *.destroy();
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/*
Аннотация показывающая какую страницу отобразить
при обращении к данному сервлету. В данном случае
в разделе "Структура проекта" настройки на:
http://localhost:8080/first - это и есть наш запрос
(request) к сервлету, а ответом (response) будет
HTML страница с надписью: "Hello from first Servlet!"
*/
@WebServlet("/first")
public class FirstServlet extends HttpServlet {
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
        /* Ответ на наш запрос */
        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter writeToWebPage = resp.getWriter()){
            writeToWebPage.write("<h1>Hello from first Servlet!</h1>");
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