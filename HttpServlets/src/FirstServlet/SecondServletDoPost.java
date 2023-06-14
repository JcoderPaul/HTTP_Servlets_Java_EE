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
правильно тип запроса - POST и прописать
параметры, например:
param : 2972e
value : test.

В GET запросе эти параметры обычно,
передаются в самой строке HTML через знак
вопроса '?'
*/
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/*
Аннотация показывающая какую страницу
отобразить при обращении к данному сервлету.
*/
@WebServlet("/second")
public class SecondServletDoPost extends HttpServlet {
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
        System.out.println("***********************************************************\n");

        String paramVolue = req.getParameter("param");
        Map<String, String[]> reqParameterMap= req.getParameterMap();
        for (Map.Entry reqParamPrn: reqParameterMap.entrySet()) {
            System.out.println(reqParamPrn.getKey() + " " + reqParamPrn.getValue());
        }


        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter writeToWebPage = resp.getWriter()){
            writeToWebPage.write("<h1>Second Servlet is here!</h1>");
        }
    }
    /* Метод занимающийся обработкой POST запроса */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                                        throws ServletException, IOException {
        Map<String, String[]> reqParamMap = req.getParameterMap();
        for (Map.Entry prn: reqParamMap.entrySet()) {
            System.out.println(prn.getKey() + " : " + prn.getValue());
        }

        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter writeToWebPage = resp.getWriter()){
            writeToWebPage.write("<h1>Second Servlet response after POST request with PARAM!</h1>");
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