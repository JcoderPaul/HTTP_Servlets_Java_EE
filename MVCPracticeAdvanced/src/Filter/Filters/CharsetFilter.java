package Filter.Filters;
/*
Выбираем библиотеку JAKARTA при выборе имплиментируемого интерфейса.
У данного интерфейса есть классические методы сервлетов init и destroy
(они дефолтные и их, естественно, можно переопределить).

Наш первый фильтр просто установит кодировку на странице к которой
обратился пользователь (и на ответ сервера).
*/
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
В отличие от классического сервлета у фильтра аннотация @WebFilter,
методы init и destroy, у него тоже есть. В выбранном случае, а именно
"/*" наш сервлет фильтр будет срабатывать на все запросы пользователя
из-за значка астериск.

В параметрах можно задавать несколько URL паттернов через запятую или
имена сервлетов, где будет применяться данный фильтр. Если они заранее
прописаны в каждом сервлете, например так:

@WebServlet(value = "/registration", name = "UserRegForm"))
*/
@WebFilter("/*")
public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /* Устанавливаем кодировку на запрос и ответ */
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        /*
        Нам необходимо решить сразу вернуть ответ по запросу пользователя
        или передать запрос/ответ дальше по цепочке, либо другому фильтру,
        либо сервлету обрабатывающему запросы по заданному URL
        */
        filterChain.doFilter(servletRequest, servletResponse);
    }
}