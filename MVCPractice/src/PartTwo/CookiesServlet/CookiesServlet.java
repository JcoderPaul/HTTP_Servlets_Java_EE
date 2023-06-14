package PartTwo.CookiesServlet;
/* Текущая настройка у меня http://localhost:8080/my-first-cookies */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/my-first-cookies")
public class CookiesServlet extends HttpServlet {
    private static final String UNIQUE_ID = "userId";
    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] clientReqCookies = req.getCookies();

        if (clientReqCookies == null || Arrays.stream(clientReqCookies).
                filter(cookie -> UNIQUE_ID.equals(cookie.getName())).
                findFirst().
                isEmpty()) {
            Cookie respCookieFromServer = new Cookie(UNIQUE_ID,"1");
            respCookieFromServer.setPath("/my-first-cookies");
            respCookieFromServer.setMaxAge(1600);
            resp.addCookie(respCookieFromServer);
            counter.incrementAndGet();
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try(PrintWriter writeToResponseHtml = resp.getWriter()){
            writeToResponseHtml.write(counter.get());
        }
    }
}
