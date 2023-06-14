package HTTP_MultiThreadServer;

/* Запускаем наш однопоточный сервер на порту 9999 */

public class MTServerRunner {
    public static void main(String[] args) {
        PoolThreadHttpServer myHttpServer = new PoolThreadHttpServer(9999, 100);
        myHttpServer.runServer();
    }
}
