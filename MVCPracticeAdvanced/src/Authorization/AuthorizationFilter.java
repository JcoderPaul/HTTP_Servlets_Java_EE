package Authorization;

import AirportSimulatorTwo.DTO.ReadUserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    /*
    Данная переменная определяет список страниц, которые
    доступны пользователю без регистрации и аутентификации.

    Данная строка используется без интернационализации и локализации:
    private static final Set<String> PUBLIC_PATH = Set.of("/login","/registration","/images");

    строка ниже применяется для тестирования кода с интернационализацией:
    */
    private static final Set<String> PUBLIC_PATH = Set.of("/international_login",
                                                          "/registration",
                                                          "/images",
                                                          "/locale");

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String enterUri = ((HttpServletRequest) servletRequest).getRequestURI();
        /*
        Если страница определена методом *.isPublicPath()
        как публичная, или пользователь 'залогинился', то
        страница пользователю отобразится.
        */
        if(isPublicPath(enterUri) || isUserLoggedIn(servletRequest)) {
            /* В случае true в одном из вариантов запрос пройдет по цепочке фильтров */
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            /*
            В случае false запрос будет переброшен либо на предыдущую
            страницу (т.е. ту откуда пришел запрос), либо на страницу
            где будет предложено 'залогинится'.

            Хедер 'referer' говорит нам с какой страницы пришел пользователь.
            */
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/international_login");
            /*
            В случае тестирования настроек без интернационализации и локализации применять текущую строку:
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
            */
        }
    }
    /* Проверяем 'залогинился' ли пользователь */
    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        ReadUserDto loginUser = (ReadUserDto) ((HttpServletRequest) servletRequest).
                                                            getSession().
                                                            getAttribute("user");
        /*
        Можно усилить фильтрацию (аутентификацию) используя параметр Role:
        return loginUser != null && loginUser.getRole() == Role.ADMIN;
        и т.п.
        */
        return loginUser != null;
    }
    /*
    Метод возвращающий true если запрошенная пользователем страница
    доступна без специального разрешения т.е. задана в переменной
    (в поле) PUBLIC_PATH как публичная.
    */
    private boolean isPublicPath(String enterUri) {
        return PUBLIC_PATH.stream().anyMatch(path -> enterUri.startsWith(path));
    }
}