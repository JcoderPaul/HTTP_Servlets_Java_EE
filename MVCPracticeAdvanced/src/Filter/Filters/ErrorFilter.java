package Filter.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/*
Сработает на страницу имитирующую ошибку, для усиления
добавив параметр dispatcherTypes, который позволяет
перенаправить запрос на страницу с ошибкой. Параметры
диспатчера FORWARD и INCLUDE можно применять для запуска
фильтра при перенаправлении запроса между веб-сервлетами.
*/
@WebFilter(value = "/ErrorImitation",
           dispatcherTypes = DispatcherType.ERROR)
public class ErrorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
