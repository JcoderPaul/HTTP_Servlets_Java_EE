package UDP_Datagrams;

import java.io.IOException;
import java.net.*;

public class DatagramSocketClient {

    public static void main(String[] args) throws IOException {
        /* Сервер находится на той же машине, где и клиент отправляющий пакет данных */
        InetAddress udpServerAddress = InetAddress.getByName("localhost");
        /* Создаем датаграм-сокет для отправки сообщения. Никаких входящих или исходящих потоков */
        try (DatagramSocket myClientDatagramSocket = new DatagramSocket()){
            byte[] bytesToSend = "Hello from UDP client!!!".getBytes();
            /*
            В отличие от TCP протокола, в случае UDP мы отправляем пакеты.
            Socket в данном случае один и для сервера и для клиента -
            DatagramSocket. При отправке пакета мы должны указать место
            назначения, т.е. адрес и порт сервера, а так же набор данных
            и объем оных. Размер отправляемого массива должен быть заранее
            согласован и на отправляющей и на принимающей стороне, чтобы
            избежать потери данных.
            */
            DatagramPacket sendPacket =
                    new DatagramPacket(bytesToSend, bytesToSend.length, udpServerAddress, 7777);
            /*
            Отправляем пакет на сервер. Нас не волнует есть ли он по указанному адресу.
            Методы *.receive() и метод *.send() ничего не возвращают они выполняют работу.
            */
            myClientDatagramSocket.send(sendPacket);
        }
    }
}
