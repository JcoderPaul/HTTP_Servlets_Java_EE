package HTTP_SingleThreadServer;

/* Запускаем наш однопоточный сервер на порту 9999 */

public class MyHttpServerRunner {
    public static void main(String[] args) {
        FirstHttpServer myHttpServer = new FirstHttpServer(9999);
        myHttpServer.runServer();
    }
}
