package ImageServlet;

import AirportSimulatorTwo.Service.ImageService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.InputStream;

@WebServlet("/Image/*")
public class ImageServlet extends HttpServlet {
    
    private final ImageService imageService = ImageService.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestUri = req.getRequestURI();
        String imagePath = requestUri.replace("/Image","");

        /*
        Если картинка на сервере есть, то мы возвращаем поток данных в
        response, перед этим установив тип передаваемого контента и
        собственно пишем данные в выходной поток методом writeImage,
        если же ее нет (файла нет) мы возвращаем 404 страницу.
        */
        imageService.getImage(imagePath).
                ifPresentOrElse(image -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(image, resp);
                }, () -> resp.setStatus(404));
    }
    
    /*
    Метод позволяет передать наш файл (картинку) от сервера к пользователю,
    т.е. преобразовывает входящий поток байт в исходящий из сервлета поток
    байт.
    */
    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try(image; ServletOutputStream outputStream = resp.getOutputStream()){
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
