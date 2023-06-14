package Authentication;

import AirportSimulatorTwo.DTO.ReadUserDto;
import AirportSimulatorTwo.Service.UserService;
import AirportSimulatorTwo.Util.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    /* Вызываем UserService, чтобы использовать его методы */
    private final UserService userService = UserService.getInstance();
    /* Перенаправляем GET запрос от пользователя на login.jsp */
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
                                       throws ServletException, IOException {

        req.getRequestDispatcher(JspPathHelper.getJspPath("login")).forward(req, resp);
    }
    /* Обрабатываем полученный POST запрос */
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                                       throws ServletException, IOException {
        /* Получаем из запроса параметры email и password */
        String enterEmail = req.getParameter("email");
        String enterPassword = req.getParameter("password");
        /* Обрабатываем полученные данные в методе *.login класса UserService */
        userService.login(enterEmail, enterPassword).
                ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp), // Если пользователь в базе есть, то...
                        () -> onLoginFail(req, resp) // Если пользователя в базе нет, то...
                );

    }
    /* Метод обрабатывает случай когда пользователя с введенными параметрами в базе нет */
    @SneakyThrows
    private void onLoginFail(HttpServletRequest req,
                             HttpServletResponse resp){
        /*
        Перенаправляем на сервлет с URL /login с параметрами
        '?error&email=" + req.getParameter("email")'
        */
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }
    /* Метод обрабатывает случай когда пользователь с введенными параметрами в базе есть */
    @SneakyThrows
    private void onLoginSuccess(ReadUserDto user,
                                HttpServletRequest req,
                                HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/flights");
    }

}
