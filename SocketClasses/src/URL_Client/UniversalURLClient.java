package URL_Client;

/*
Слегка странный способ чтения данных из файла. Мы используем тот же класс URL, что и в прошлом примере, когда делали GET запрос к https://ya.ru.
Однако тут мы используем его несколько нестандартно. Мы читаем содержимое файла на нашем диске.
*/

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UniversalURLClient {
    public static void main(String[] args) throws IOException {
        
        /*
        Логика действий та-же:
        1. Создаем объект URL, и в качестве аргумента передаем местоположение нашего файла;
        2. Наш объект URL открывает соединение;
        3. Используя методы доступные из открытого соединения, читаем данные из файла;
        4. Используем полученную информацию, в нашем случае, выводим на экран.
        */
        
        URL readFileByURLConnection = new URL("file:src/TCP_Sockets/SocketClient/SimpleSocketClient.java");
        URLConnection connectionForReadFile = readFileByURLConnection.openConnection();
        String readAllBytesFromFile = new String(connectionForReadFile.getInputStream().readAllBytes());
        System.out.println(readAllBytesFromFile);
    }
}
