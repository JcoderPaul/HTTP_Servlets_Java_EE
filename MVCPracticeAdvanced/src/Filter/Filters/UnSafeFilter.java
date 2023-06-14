package Filter.Filters;
/*
Хитрость фильтров в том, что ответ фильтром может быть
возвращен пользователю сразу после обработки запроса и
даже не обращаться к целевому сервлету и таких фильтров
может быть целый каскад (см. ServletFilter.jpg).
*/
import AirportSimulatorTwo.DTO.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/unsafe")
public class UnSafeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /*
        Постоянное приведение типов необходимо для получения
        доступа к требуемым методам. В данном случае получаем
        атрибут пользователь
        */
        UserDto myUser = (UserDto) ((HttpServletRequest) servletRequest).
                                                         getSession().
                                                         getAttribute("user");
        /*
        Если данные нашего пользователя не null, то пользователь
        попадет на страницу с URL '/unsafe' в противном случае
        фильтр перенаправит пользователя на сервлет с URL
        '/registration'
        */
        if (myUser != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            /*
            Как и выше HttpServletResponse расширяет servletResponse
            и чтобы использовать *.sendRedirect() используем приведение
            типов
            */
            ((HttpServletResponse) servletResponse).sendRedirect("/registration");
        }
    }
}