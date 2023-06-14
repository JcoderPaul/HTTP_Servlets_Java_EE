package TCP_Sockets.SocketClient;

import java.io.*;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String[] args) throws IOException {
        /*
        1. Создаем клиентский Socket (гнездо, розетка), через
        который будет послан запрос к серверу, в нашем случае
        к серверу Google. Через TCP протокол на порт 80
        (http - 80, https - 443).

        2. Открытое соединение и поток всегда нужно закрывать
        по завершении программы, поэтому в данном примере мы
        помещаем всю последовательность
        'соединения-запроса данных-получения ответа' в блок
        try-with-resources,
        */
        try(Socket firstSocket = new Socket("google.com",80);
            DataOutputStream outputStream = new DataOutputStream(firstSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(firstSocket.getInputStream())){
            /* Посылаем запрос к серверу */
            outputStream.writeUTF("Hello Google!");
            /* Получаем ответ от сервера */
            byte[] responseFromGoogle = inputStream.readAllBytes();

            System.out.println(responseFromGoogle.length);
        }
    }
}