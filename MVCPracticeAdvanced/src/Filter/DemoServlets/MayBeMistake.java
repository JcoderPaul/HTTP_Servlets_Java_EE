package Filter.DemoServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MayBeMistake",
            description = "This imitation of error action",
            urlPatterns = "/ErrorImitation")
public class MayBeMistake extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
                                  throws ServletException, IOException {
        if(true){
            throw new RuntimeException();
        }
    }
}
