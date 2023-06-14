package TCP_Sockets.SimpleMessenger;
/*
В цепочке:
'открыть соединение -
 отправить запрос -
 получить ответ -
 закрыть соединение'
мы можем не закрывать соединение, а
обмениваться сообщениями с сервером
'бесконечно долго'.

TCP - протокол это позволяет.
*/
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMessager {
    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = Inet4Address.getByName("localhost");

        try(Socket firstSocket = new Socket(inetAddress,6666);
            DataOutputStream clientOutputStream = new DataOutputStream(firstSocket.getOutputStream());
            DataInputStream clientInputStream = new DataInputStream(firstSocket.getInputStream());
            Scanner clientScanner = new Scanner(System.in)){

            System.out.println("Send message to Server: ");
            /*
            Условно бесконечный цикл, в котором мы считываем
            сообщения с консоли и отправляем их на сервер. А
            так-же получаем сообщения от сервера и выводи их
            на экран.

            !!!
            Нужно помнить, что TCP (в отличие от UDP) соединение
            проверяет наличие соединения с сервером и в данном
            примере, мы никак не обрабатываем разрыв соединения с
            сервером. Поэтому, когда клиент отправит серверу
            сообщение 'stop' - сервер выйдет из цикла, грубо
            разорвет соединение и окончит работу. А клиентская
            программа поймает исключение.
            !!!
            */
            while(clientScanner.hasNextLine()){
                String requestFromClient = clientScanner.nextLine();
                clientOutputStream.writeUTF(requestFromClient);
                System.out.println("Response from Server: " + clientInputStream.readUTF());
            }
        }
    }
}