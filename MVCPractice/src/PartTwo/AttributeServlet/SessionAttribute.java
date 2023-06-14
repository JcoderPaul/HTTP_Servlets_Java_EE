package PartTwo.AttributeServlet;
/*
В качестве примера поместим в атрибуты пользователя, как это
делается при аутентификации на многих сайтах. Естественно в
нашем случае это приближенный, к реальности вариант. Но, как
и в случае с сессией, нам необходимо запомнить пользователя
на стороне сервера и "не требовать от пользователя повторного
введения данных", т.е. не выполнять часть кода после проверки.

В данном примере все "задается принудительно" и демонстрацию
мы можем наблюдать только через браузер и DEBUG. В браузере
нужно открыть 'Инструменты разработчика' и воспользоваться
разделом 'Network' для отслеживания состояния Request и
Response хедеров. Раздел 'Application' позволяет отслеживать
состояние JSESSIONID cookie.

!!!
Мощь АТРИБУТОВ в том, что они есть у request-ов и у
servlet context - а, см. Attributes.jpg.
!!!
*/
import AirportSimulator.DTO.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/set-attribute")
public class SessionAttribute extends HttpServlet {

    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Получаем параметры текущей сессии */
        HttpSession mySession = req.getSession();
        /*
        Получаем атрибуты текущей сессии, а именно USER-а. Поскольку в качестве
        атрибута можно передавать любой аргумент Object, мы вынуждены применить
        кастомизацию атрибута USER.

        Теперь нам нужно понять, заходил ли юзер с нашими параметрами на сервер
        или нет, поэтому мы сначала запрашиваем нужный атрибут и проверяем, не
        null ли он и если так, то задаем параметры USER.
        */
        UserDto incomingUser = (UserDto) mySession.getAttribute(USER);
        /*
        Несложно заметить используя режим Debug что при первом обращении в браузере
        к http://localhost:8080/set-attribute значение атрибута
        incomingUser будет равно null и мы проходим весь кусок кода после IF, т.е.
        имитируем аутентификацию нашего USER на сервера.

        При повторном обращении к http://localhost:8080/set-attribute
        в переделах одной сессии мы видим, что атрибут USER уже задан, мы пропускаем
        код в пределах IF.

        Естественно если в браузере мы удалим JSESSIONID из cookie браузера, сервер не
        сможет ассоциировать нашу текущую сессию с заданным пользователем, т.к. в
        ассоциативном массиве где хранятся все данные, повторное обращение к странице
        будет, как новая сессия (без заданных параметров USER). Как и при самом первом
        обращении к http://localhost:8080/set-attribute результат метода
        mySession.isNew() будет true.

        !!!
        Мощь АТРИБУТОВ в том, что они есть у request-ов и у сессий - HttpSession и у
        глобального ServletContext - а, см. Attributes.jpg. И если их задать у Context - а,
        то они будут доступны у всех сессий и у всех запросов.
        !!!
        */
        if(incomingUser == null){
            incomingUser = UserDto.builder().
                    id(25L).
                    mail("first_user@yandex.ru").
                    build();
            mySession.setAttribute(USER,incomingUser);
        }
    }
}