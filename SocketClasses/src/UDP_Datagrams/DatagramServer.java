package UDP_Datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServer {

    public static void main(String[] args) throws IOException {
        try(DatagramSocket serverDatagramSocket = new DatagramSocket(7777)){
            byte[] readReceiveBuffer = new byte[128];
            DatagramPacket receivePacket =
                    new DatagramPacket(readReceiveBuffer, readReceiveBuffer.length);
            /* Методы *.receive() и метод *.send() ничего не возвращают они выполняют работу */
            serverDatagramSocket.receive(receivePacket);

            /*
            Передаем содержимое нашего буфера в String, благо
            мы знаем, что была отправлена - принята строка. И
            выводим на экран.
            */
            String receiveDataFromClient = new String(readReceiveBuffer).trim();
            System.out.println(receiveDataFromClient);
        }
    }
}
