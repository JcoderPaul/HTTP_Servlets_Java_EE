package TCP_Sockets.SocketClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleSocketRunner {
    public static void main(String[] args) throws IOException {
        /*
        Теперь наш клиент отправит сообщение нашему серверу,
        т.к. он находится на той-же самой машине, то мы
        обращаемся к "локалхосту", а порт указываем, то на
        котором слушает наш (SocketServerRunner) сервер - 7777
        */
        InetAddress inetAddress = Inet4Address.getByName("localhost");

        try(Socket firstSocket = new Socket(inetAddress,7777);
            DataOutputStream outputStream = new DataOutputStream(firstSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(firstSocket.getInputStream())){

            /* Посылаем запрос к серверу */
            outputStream.writeUTF("Hello my first SERVER!!!");
            /* Получаем ответ от сервера, мы точно знаем, что это строка и мы ее ждем */
            System.out.println("Response from SERVER: " + inputStream.readUTF());
        }
        /*
        Для проверки работоспособности нашего клиент-серверного
        соединения, мы первым запускаем наш сервер, который ожидает
        внешнего запроса.

        Далее, мы запускаем наш клиент, который шлет нашему серверу
        сообщение: Hello my first SERVER!!!

        Сервер в свою очередь получив сообщение из вне отправляет
        ответ: Hello from server!!!

        Таким образом работает протокол HTTP:
        1. Open Connection
        2. Send Request
        3. Receive Response
        4. Close Connection
        */
    }
}