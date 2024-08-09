package HTTP_Client;

/*
После Java 11, наиболее предпочтительным классом для взаимодействия приложений с HTTP серверами является класс 
HttpClient, а не более ранний класс URL. HttpClient потокобезопасен и может работать в асинхронном режиме.
*/

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        /*
        Создаем HTTP клиент через билдер и задаем версию протокола 1.1. Теперь, используя этот клиент, мы можем отправлять запросы к HTTP серверу.

        При этом данный объект уже на стадии создания имеет массу возможностей или методов для обращения к HTTP серверу в различных режимах 
        (аутентификация, соединение по таймауту, выбор приоритета и т.д.).
        */
        
        HttpClient myFirstHttpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        
        /* Создаем запрос к HTTP серверу, объект HttpRequest, в котором мы можем определить метод отправки запроса: GET, POST, DELETE и т.д. */
        
        HttpRequest myRequest = HttpRequest
                .newBuilder(URI.create("https://ya.ru"))
                .GET() // Выбрали метод запроса
                .build();
        
        /* Метод *.send() позволяет отправить запрос к HTTP серверу, при этом есть возможность отправить запрос в синхронном и асинхронном режиме (получив CompletableFuture ответ). */
        HttpResponse<String> responseToMeFromServer = myFirstHttpClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        
        /* Получаем "заголовки" - хедеры отправленные HTTP сервером в ответ на наш запрос */
        Map responseHeaders = responseToMeFromServer.headers().map();
        responseHeaders.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
        System.out.println("\n**************************************************************************\n");
        /* Получаем HTML страницу, сформированную сервером, в ответ на наш запрос */
        String responseBody = responseToMeFromServer.body();
        System.out.println(responseBody);
    }
}
