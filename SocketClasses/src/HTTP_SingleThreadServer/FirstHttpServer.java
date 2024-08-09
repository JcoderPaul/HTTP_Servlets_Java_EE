package HTTP_SingleThreadServer;

/* Создаем простой, однопоточный HTTP сервер, который принимает некие запросы и генерирует некий стандартный ответ. */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class FirstHttpServer {
    private final int port;    // Для создания сервера, нам понадобится порт, который он будет прослушивать, этот параметр мы передадим в ServerSocket
    public FirstHttpServer(int port) { this.port = port; }    // Передаем интересующий нас порт через конструктор
    /* Метод запускающий наш сервер */
    public void runServer(){
        /*
        1. Создаем ServerSocket и передаем ему порт;
        2. Далее создаем клиентский Socket;
        3. И затем, только при получении запроса от
           клиента, обрабатываем полученный запрос.
        */
        try {
            ServerSocket myFirstServer = new ServerSocket(port);
            /*
            Метод *.accept() принимает соединение от нашего клиента. Данный метод блокирующий и будет ожидать запроса извне и
            только затем он отпускает поток. Далее мы обрабатываем полученный запрос, передав в него наш клиентский Socket.
            */
            Socket clientSocket = myFirstServer.accept();
            processingSocketRequest(clientSocket);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    /* Метод обрабатывающий запрос от клиента */
    private void processingSocketRequest(Socket clientSocket){
        /*
        Открываем два потока входящий и исходящий для получения запросов - request и формирования ответов - response.

        Естественно в реальности сервер ведет более сложную работу по обработке запросов и выдаче соответствующих ответов, 
        в зависимости от полученного запроса. Но суть не меняется.
        */
        try(DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())) {
            /* 1. Получаем запрос и обрабатываем его (handle request). Выводим на экран запрос от HttpClientTester клиента. */
            System.out.println("Request: \n" + new String(dataInputStream.readNBytes(456)));
            
            /*
            2. Обрабатываем запрос и формируем ответ (handle response).
               Формируем тело ответа, например, передав в него наш html
               документ из папки 'resources'.

               Это будет тело - body нашего ответа.
            */
            byte[] responseBody = Files.readAllBytes(Path.of("resources","htmlExample.html"));
            
            /* Формируем header-ы ответа */
            byte[] responseHeaders = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(responseBody.length).getBytes();
            
            /* Сервер отправляет ответ клиенту */
            dataOutputStream.write(responseHeaders);
            dataOutputStream.write(System.lineSeparator().getBytes());
            dataOutputStream.write(responseBody);

        } catch (IOException ioException){
            /*
            В данном случае мы не пробрасываем исключения выше, а обрабатываем полученные ошибки, чтобы сервер не остановился. 
            Например, мы логгируем ошибки, а сервер продолжает обрабатывать другие запросы, от других клиентов.
            */
            ioException.printStackTrace();
        } finally {
            /* Закрываем клиентский Socket */
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
