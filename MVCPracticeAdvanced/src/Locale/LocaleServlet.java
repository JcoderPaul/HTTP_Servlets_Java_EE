package Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        String language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);

        String prevPage = req.getHeader("referer");
        String thisPage = prevPage != null ? prevPage : "/international_login";
        resp.sendRedirect(thisPage + "?lang=" + language);
    }
}