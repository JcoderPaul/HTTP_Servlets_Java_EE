package URL_Client;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;

public class SimpleURLClient {
    public static void main(String[] args) throws IOException {
        /*
        Создаем объект класса URL, который работает по протоколу
        HTTP. По умолчанию, при открытии соединения, этот объект
        запрашивает данные с указанного URL адреса. Т.е. формирует
        запрос *.get и получает соответствующий отклик от сервера,
        который мы можем извлечь и использовать.

        Естественно он может работать с другими методами HTTP: POST,
        PUT и т.д.
        */
        URL myFiestUrlObject = new URL("https://ya.ru");
        /* Открываем соединение */
        URLConnection myFirstUrlConnection = myFiestUrlObject.openConnection();
        /* Получаем карту полей и их значений из ответа сервера */
        Map<String, List<String>> urlFields = myFirstUrlConnection.getHeaderFields();

        for (Map.Entry prn: urlFields.entrySet()) {
            System.out.println(prn.getKey() + " : " + prn.getValue());
        }
    }
}
