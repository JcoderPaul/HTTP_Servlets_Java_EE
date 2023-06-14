package AirportSimulatorTwo.Servlet.UserRegForm;

/* http://localhost:8080/registration

Логика нашего приложения была описана в первой части,
см. DOC/MVC_Chart_with_comment.jpeg, три глобальных
уровня связанных логически, и четыре технологических
уровня (общающиеся между собой реализуя принципы MVC):
- HTML/JSP - страницы (View) общается с сервлетами;
- Servlets - страницы (Controller) общается с уровнем сервисов;
- Services - java классы (Model) приемо-передатчики между уровнем
             DAO и Servlet, а фактически между Model и Controller;
- DAO - java классы (Model)позволяющие максимально просто
        пользователю общаться с базой данных (реализуют CRUD принципы);

Для работы с пользователями создадим в базе данных
таблицу пользователей: см. create_users_table.sql
*/

import AirportSimulatorTwo.DTO.CreateUserDto;
import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Entity.EntityEnum.Role;
import AirportSimulatorTwo.Exception.ValidationException;
import AirportSimulatorTwo.Service.UserService;
import AirportSimulatorTwo.Util.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/*
По умолчанию сервлеты не могут работать с элементами Part, поскольку мы в
форме регистрации пользователя пытаемся загрузить бинарный файл, нам
понадобится аннотация @MultipartConfig, которая позволяет реализовать
необходимый функционал и принимает следующие параметры:

- location - Абсолютный путь к каталогу в файловой системе. Атрибут местоположения
             не поддерживает путь относительно контекста приложения. Это расположение
             используется для временного хранения файлов во время обработки частей
             или когда размер файла превышает указанный параметр fileSizeThreshold.
             Расположение по умолчанию - default "".
- maxFileSize - Размер файла в байтах, после которого файл будет временно храниться
                на диске. Размер по умолчанию — 0 байт (default -1L).
- maxRequestSize - Максимально допустимый размер загружаемых файлов в байтах. Если
                   размер любого загруженного файла больше этого размера, веб-контейнер
                   выдаст исключение (IllegalStateException). Размер по умолчанию не
                   ограничен (default -1L). Фактически это размер всего request-а.
- fileSizeThreshold - Максимальный размер, допустимый для запроса multipart/form-data,
                      в байтах. Веб-контейнер выдаст исключение, если общий размер всех
                      загруженных файлов превысит этот порог. Размер по умолчанию не
                      ограничен (default 0).
                      !!! Данный размер определяет файл какого размера будет висеть в
                      памяти без загрузки на диск по пути указанному в параметре location !!!

Например, аннотация @MultipartConfig может быть построена следующим образом:
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
                 maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)

Более подробно: https://docs.oracle.com/javaee/6/tutorial/doc/gmhal.html
*/
@MultipartConfig(fileSizeThreshold=1024*1024)
@WebServlet("/registration")
public class UserRegForm extends HttpServlet {

    private UserService userService = UserService.getInstance();
    /*
    На doGet возвращаем *.jsp страничку на которой мы
    введем данные нашего пользователя и передадим эти
    сведения в базу данных через POST запрос
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
                                                                           ServletException,
                                                                           IOException {
        req.setAttribute("roles", Role.values()); // Role.values() подобен List.of("USER","ADMIN")
        req.setAttribute("genders", Gender.values()); // Gender.values() подобен List.of("MALE","FEMALE")

        req.getRequestDispatcher(JspPathHelper.getJspPath("jstl_img_reg_form")).forward(req,resp);
    }
    /* На doPost возвращаем запрос */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
                                                                        ServletException, IOException {

        /*
        Данная форма регистрации в отличие от формы в AirportSimulator
        имитирует сохранение некоего загружаемого пользователем файла
        (картинки) извне на сервер (необходимо понимать, что наш TomCat
        это, вроде как, некий сервер в интернете и мы общаемся с ним
        через браузер)
        */
        Part loadImg = req.getPart("image");
        /*
        Извлекаем все необходимые параметры из нашей
        формы регистрации (уровень view), чтобы передать
        на уровень сервисов (уровень controller)
        */
        CreateUserDto userDto = CreateUserDto.builder().
                name(req.getParameter("name")).
                birthday(req.getParameter("birthday")).
                email(req.getParameter("email")).
                image(loadImg).
                password(req.getParameter("password")).
                role(req.getParameter("role")).
                gender(req.getParameter("gender")).
                build();

        try {
            /*
            Создаем пользователя на уровне Service и
            перенаправляем запрос на сервлет login
            */
            userService.create(userDto);
            resp.sendRedirect("/registration");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getApp_errors());
            doGet(req, resp);
        }
    }
}
