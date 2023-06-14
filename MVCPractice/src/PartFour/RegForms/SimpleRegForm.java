package PartFour.RegForms;

/* http://localhost:8080/simple-reg-form */

import PartThree.UtilHelper.JspPathHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/simple-reg-form")
public class SimpleRegForm extends HttpServlet {
    /* На doGet возвращаем *.jsp страничку */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
                                                                    ServletException, IOException {
        req.getRequestDispatcher(JspPathHelper.getJspFormPath("simpleReg")).forward(req,resp);
    }
    /* На doPost возвращаем запрос */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
                                                                    ServletException, IOException {
        String name = req.getParameter("name");
    }
}
