package HTTP_MultiThreadServer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpRequestRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        1. Создаем наш HTTP клиент, который
           будет формировать запрос к серверу
        */
        HttpClient myHttpClientToServerTest = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        /*
        2. Формируем запрос к серверу, в котором мы
           отправим наш jsom файл из папки 'resources'
           в качестве запроса. Запрос идет методом
           POST и содержит тело - наш json файл.
        */
        HttpRequest myRequestToServer = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:9999")) // Указываем куда идет запрос и на какой порт
                .header("content-type", "application/json")
                .POST(ofFile(Path.of("resources","myFirst.json")))
                .build();
        /*
        3. Отправляем запрос к серверу используя метод *.send(),
           куда мы передаем наш запрос. Ответом будет обычная
           html страница - обычный текстовый файл.
        */
        CompletableFuture <HttpResponse<String>> responseFromServer =
                myHttpClientToServerTest
                        .sendAsync(myRequestToServer, HttpResponse.BodyHandlers.ofString());
        CompletableFuture <HttpResponse<String>> responseFromServerTwo =
                myHttpClientToServerTest
                        .sendAsync(myRequestToServer, HttpResponse.BodyHandlers.ofString());
        CompletableFuture <HttpResponse<String>> responseFromServerThree =
                myHttpClientToServerTest
                        .sendAsync(myRequestToServer, HttpResponse.BodyHandlers.ofString());
        /* Выводим ответ сервера для 3-го запроса на экран */
        try {
            System.out.println(responseFromServerThree.get().body());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
