package Filter.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Arrays;

/*
Аннотация фильтр с параметром, который запустит
наш сервлет фильтр на любой запрос, т.е. мы
'логируем' все запросы к нашему саерверу.
*/
@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /*
        Получаем карту параметров (Ключ, Значение) и отображаем их в консоли.
        Значение ключей (параметров) в свою очередь тоже списки
        */
        servletRequest.getParameterMap().
                forEach((Key, Val) -> System.out.println(Key + ":" + Arrays.toString(Val)));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
