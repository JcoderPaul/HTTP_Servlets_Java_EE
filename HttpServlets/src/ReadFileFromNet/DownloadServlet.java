package ReadFileFromNet;
/*
В данном примере мы демонстрируем скачивание
файла и сразу же пишем в него информацию. Т.е.
некий пользователь 'сделал запрос на наш сервер'
и попытался скачать некий файл. Естественно вся
необходимая информация для скачивания находится
в хедерах.
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/download-file")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                       throws ServletException, IOException {
        /*
        При скачивании файла нам нужен заголовок с названием
        'Content-Disposition' и параметры (значения заголовка)
        с именем файла для скачивания.
        */
        resp.setHeader("Content-Disposition","attachment; filename=\"myfile.txt\"");
        resp.setContentType("text/plain"); // Говорим, что контент обычный текст
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); // Задали кодировку

        /*
        Пишем текстовую информацию, а не байты в скачиваемый
        (возвращаемый) по запросу текстовый файл. Текст пишем
        в файл "налету".
        */
        try(PrintWriter myWriter = resp.getWriter()){
            myWriter.write("Write data from 'DownloadServlet'!");
        }
    }
}
