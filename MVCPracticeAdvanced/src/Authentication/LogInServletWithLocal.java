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

@WebServlet("/international_login")
public class LogInServletWithLocal extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
                                       throws ServletException, IOException {

        req.getRequestDispatcher(JspPathHelper.getJspPath("international_login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                                       throws ServletException, IOException {
        String enterEmail = req.getParameter("email");
        String enterPassword = req.getParameter("password");
        userService.login(enterEmail, enterPassword).
                ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );

    }
    @SneakyThrows
    private void onLoginFail(HttpServletRequest req,
                             HttpServletResponse resp){
        resp.sendRedirect("/international_login?error&email=" + req.getParameter("email"));
    }
    @SneakyThrows
    private void onLoginSuccess(ReadUserDto user,
                                HttpServletRequest req,
                                HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/flights");
    }

}
