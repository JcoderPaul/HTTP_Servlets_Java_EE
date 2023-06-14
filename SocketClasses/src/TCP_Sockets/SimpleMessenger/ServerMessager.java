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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMessager {
    public static void main(String[] args) throws IOException {

        System.out.println("Server is waiting for a messages ... ");

        try(ServerSocket myServerSocket = new ServerSocket(6666);
            Socket myOuterConnectionSocket = myServerSocket.accept();
            DataOutputStream serverOutputStream =
                    new DataOutputStream(myOuterConnectionSocket.getOutputStream());
            DataInputStream serverInputStream =
                    new DataInputStream(myOuterConnectionSocket.getInputStream());
            Scanner serverScanner = new Scanner(System.in)){

            String requestForSocketClient = serverInputStream.readUTF();

            while(!"stop".equals(requestForSocketClient)){
                System.out.println("Client request: " + requestForSocketClient);
                System.out.println("Send message to Client: ");
                String responseFromServer = serverScanner.nextLine();
                serverOutputStream.writeUTF(responseFromServer);
                requestForSocketClient = serverInputStream.readUTF();
            }
        }
    }
}
