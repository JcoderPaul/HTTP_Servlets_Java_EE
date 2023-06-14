package ReadFileFromNet;
/*
В данном примере мы демонстрируем скачивание
файла в формате json. Т.е. некий пользователь
'сделал запрос на наш сервер' и попытался
скачать файл myFirst.json из папки ресурсов.
Естественно вся необходимая информация для
скачивания находится в хедерах.
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

@WebServlet("/download-json-file")
public class DownloadServletTwo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                                       throws ServletException, IOException {
        /*
        При скачивании файла нам нужен заголовок с названием
        'Content-Disposition' и параметры (значения заголовка)
        с именем файла для скачивания. Если учесть, что json
        формат текстовый, то мы смело можем вернуть по запросу
        не *.json файл, а файл с расширением *.txt содержащий
        json-структуру объектов.
        */
        resp.setHeader("Content-Disposition","attachment; filename=\"myjson.txt\"");
        resp.setContentType("application/json"); // Говорим, что контент обычный текст
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); // Задали кодировку

        /*
        Если мы хотим позволить скачать файл с сервера, то мы либо указываем
        полный путь до него и применяем статический метод класса Files:
        Files.readAllBytes(Path.of("resources",".../.../myFirst.json"));
        Либо при компиляции помещаем файл в ресурсную директорию и создаем
        WAR архив, и тогда путь к файлу будет определяться относительно корня.
        */
        try(ServletOutputStream outputFromServlet = resp.getOutputStream();
            InputStream streamToWrite =
                    DownloadServletTwo.class.getClassLoader().getResourceAsStream("myFirst.json")){
            outputFromServlet.write(streamToWrite.readAllBytes());
        }
    }
}
