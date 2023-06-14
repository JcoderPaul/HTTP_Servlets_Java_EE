package PartFour.RegForms;

/* http://localhost:8080/dynamic-reg-form */

import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/dynamic-reg-form")
public class DynamicRegForm extends HttpServlet {
    /* На doGet возвращаем *.jsp страничку */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
                                                                    ServletException, IOException {
        /* Вносим некую динамику в форму регистрации в отличие от SimpleRegForm */
        req.setAttribute("roles", List.of("USER","ADMIN"));
        req.setAttribute("genders", List.of("MALE","FEMALE"));

        req.getRequestDispatcher(JspPathHelper.getJspFormPath("dynamicJstlReg")).forward(req,resp);
    }
    /* На doPost возвращаем запрос */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
                                                                    ServletException, IOException {
        String name = req.getParameter("name");
    }
}
