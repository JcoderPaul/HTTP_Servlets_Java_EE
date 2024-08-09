package HTTP_SimpleServer;

/*
Следующие шаги выполняются при установлении TCP-соединения между двумя компьютерами с использованием сокетов:

1. Сервер создает объект ServerSocket, указывающий, на каком номере порта должна происходить связь.
2. Сервер вызывает метод accept() класса ServerSocket. Этот метод ожидает, пока клиент не подключится к серверу через заданный порт.
3. Пока сервер ожидает соединения с ним, клиент создает экземпляр объекта Socket, указывая имя сервера и номер порта для подключения.

Объект класса Socket пытается подключить клиента к указанному серверу и номеру порта. Если связь установлена, у клиента теперь есть 
объект Socket, способный взаимодействовать с сервером.

На стороне сервера метод accept() возвращает ссылку на новый сокет на сервере, который подключен к сокету клиента.

После того как соединения установлены, обмен данными может осуществляться с использованием потоков ввода/вывода. Каждый сокет имеет 
как OutputStream, так и InputStream. Поток вывода клиента подключен к потоку ввода сервера, а поток ввода клиента подключен к потоку 
вывода сервера.

TCP — это протокол двусторонней связи, поэтому данные могут передаваться по обоим потокам одновременно. Ниже приведены полезные классы, 
предоставляющие полный набор методов для реализации сокетов.

Для проверки работы данного сервера его можно запустить в Intellij IDEA.

Или скомпилировать: javac HTTPSimpleServer.java
И запустить через: java -cp . HTTPSimpleServer

В любом браузере обратиться к http://localhost:7777/

Содержание запрос-ответ обмена можно посмотреть средствами разработчика в браузере.
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPSimpleServer {

    public static void main(String[] args) throws Throwable {
        ServerSocket serverSocket = new ServerSocket(7777);   // Создаем СерверСокет на порту 7777
        while (true) {   // Бесконечный цикл
            Socket socketForListen = serverSocket.accept();   // Получаем клиента, который обратился к серверу
            System.err.println("HTTP Клиент прислал запрос ...");
            new Thread(new ServerSocketProcessor(socketForListen)).start();
        }
    }

    private static class ServerSocketProcessor implements Runnable {
        private Socket socketForListenClient;
        private InputStream serverInputStream;
        private OutputStream serverOutputStream;

        private ServerSocketProcessor(Socket socket) throws Throwable {
            this.socketForListenClient = socket;
            this.serverInputStream = socket.getInputStream();
            this.serverOutputStream = socket.getOutputStream();
        }

        public void run() {
            try {
                readInputHeaders();
                writeResponse("<html><body><h1>Hello from HTTP-Simple-Server!!!</h1></body></html>");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    socketForListenClient.close();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Клиент отключился!");
        }

        private void writeResponse(String resp_on_screen) throws Throwable {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: MyFirstHttpServer/17-05-2023\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + resp_on_screen.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + resp_on_screen;
            serverOutputStream.write(result.getBytes());
            serverOutputStream.flush();
        }

        private void readInputHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(serverInputStream));
            while(true) {
                String inputData = br.readLine();
                if(inputData == null || inputData.trim().length() == 0) {
                    break;
                }
            }
        }
    }
}
