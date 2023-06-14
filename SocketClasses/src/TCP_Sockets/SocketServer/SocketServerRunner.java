package TCP_Sockets.SocketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        /*
        1. IP адрес машины на который развернут наш сервер
        и будет адресом сервера (в нашем случай мы обратимся
        с нашего клиента, сами к себе, поэтому это будет
        127.0.0.1), если же мы будем обращаться с другой
        (или) к другой машине то в настройках создаваемого
        Socket - а клиента кроме TCP порта нужно указывать
        IP сервера. В случае TCPSockets.SocketServer - а достаточно TCP
        порта (у нас это 7777)
        */
        ServerSocket myServerSocket = new ServerSocket(7777);
        /*
        2. Наш ServerSocket принимает потоки ввода-вывода, как и
        обычный Socket. Чтобы наш сервер принял данные, мы применяем
        его блокирующий метод *.accept(), т.е. программа будет ждать
        пока на данный открытый Socket не придет какое-либо
        сообщение из вне.

        !!!
        Это означает, что наш сервер не может принять, более
        чем одно соединение, и для того, чтобы это было возможно
        организуется многопоточность.
        !!!
        */
        Socket myOuterConnectionSocket = myServerSocket.accept();
        /*
        3. Как и в случае с обычным Socket клиентом нас сервер
        тоже что-то принимает и что-то отправляет, а значит нам
        нужны входящие и исходящие потоки.
        */
        DataOutputStream serverOutputStream =
                new DataOutputStream(myOuterConnectionSocket.getOutputStream());
        DataInputStream serverInputStream =
                new DataInputStream(myOuterConnectionSocket.getInputStream());
        /*
        4. Выведем на экран сообщение от нашего клиента или
        запрос - request (полученные через InputStream). И
        пошлем нашему клиенту сообщение от сервера или ответ
        - response (отправленные через OutputStream).
        */
        System.out.println("Client request: " + serverInputStream.readUTF());
        serverOutputStream.writeUTF("Hello from server!!!");

        /*
        5. Закрываем все потоки и каналы принудительно. В данном
        примере мы не использовали try-with-resources, как в
        Socket - клиенте.
        */
        myServerSocket.close();
        myOuterConnectionSocket.close();
        serverOutputStream.close();
        serverInputStream.close();
        /*
        6. Для проверки работоспособности нашего клиент-серверного
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
