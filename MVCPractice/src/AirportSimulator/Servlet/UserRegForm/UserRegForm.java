package AirportSimulator.Servlet.UserRegForm;

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

import AirportSimulator.DTO.CreateUserDto;
import AirportSimulator.Entity.EntityEnum.Gender;
import AirportSimulator.Entity.EntityEnum.Role;
import AirportSimulator.Exception.ValidationException;
import AirportSimulator.Service.UserService;
import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
                                                                        ServletException, IOException {
        req.setAttribute("roles", Role.values()); // Role.values() подобен List.of("USER","ADMIN")
        req.setAttribute("genders", Gender.values()); // Gender.values() подобен List.of("MALE","FEMALE")

        req.getRequestDispatcher(JspPathHelper.getJspPath("jstl_reg_form")).forward(req,resp);
    }
    /* На doPost возвращаем запрос */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
                                                                        ServletException, IOException {
        /*
        Извлекаем все необходимые параметры из нашей
        формы регистрации (уровень view), чтобы передать
        на уровень сервисов (уровень controller)
        */
        CreateUserDto userDto = CreateUserDto.builder().
                name(req.getParameter("name")).
                birthday(req.getParameter("birthday")).
                email(req.getParameter("email")).
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
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getApp_errors());
            doGet(req, resp);
        }
    }
}
