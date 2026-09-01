- **См. исходник (ENG):**
  - [Class ServerSocket (Java SE 8)](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html)
  - [Class ServerSocket (Java SE 25)](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/net/ServerSocket.html)

---
### Класс ServerSocket

```
public class ServerSocket
            extends Object
                implements Closeable
```

**Пакеты:** [java.net](https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html)

**Все реализуемые интерфейсы:** 
- [Closeable](https://docs.oracle.com/javase/8/docs/api/java/io/Closeable.html),
- [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html)

**Известные подклассы:** [SSLServerSocket](https://docs.oracle.com/javase/8/docs/api/javax/net/ssl/SSLServerSocket.html)

Этот класс реализует серверные сокеты. Сокет сервера ожидает поступления
запросов по сети. Он выполняет некоторую операцию на основе этого запроса,
а затем, возможно, возвращает результат запрашивающей стороне.

Фактическая работа серверного сокета выполняется экземпляром класса SocketImpl.
Приложение может изменить фабрику сокетов, создающую реализацию сокета, чтобы
настроить себя на создание сокетов, соответствующих локальному брандмауэру.

**См. так же:** 
- [SocketImpl](https://docs.oracle.com/javase/8/docs/api/java/net/SocketImpl.html),
- [setSocketFactory(java.net.SocketImplFactory)](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html#setSocketFactory-java.net.SocketImplFactory-),
- [ServerSocketChannel](https://docs.oracle.com/javase/8/docs/api/java/nio/channels/ServerSocketChannel.html)

---
#### Конструкторы

- `ServerSocket()` - Создает несвязанный серверный сокет.
- `ServerSocket(int port)` - Создает серверный сокет, привязанный к указанному порту.
- `ServerSocket(int port, int backlog)` - Создает сокет сервера и привязывает его к указанному номеру локального порта с указанным количества заданий, где `port` - номер порта или '0' использовать номер порта, который назначается автоматически, `backlog` - запрошенная максимальная длина очереди входящих соединений.
- `ServerSocket(int port, int backlog, InetAddress bindAddr)` - Создайте сервер с указанным портом, журналом прослушивания и локальным IP-адресом для привязки.

---
#### Методы

- `Socket accept()` - Ожидает подключения к этому сокету и принимает его.
- `void bind(SocketAddress endpoint)` - Привязывает `ServerSocket` к определенному адресу (IP-адрес и номер порта).
- `void bind(SocketAddress endpoint, int backlog)` - Привязывает ServerSocket к определенному адресу (IP-адрес и номер порта), где endpoint- IP-адрес и номер порта для привязки, backlog- запрошенная максимальная длина очереди входящих соединений.
- `void close()` - Закрывает этот сокет.
- `ServerSocketChannel getChannel()` - Возвращает уникальный объект `ServerSocketChannel`, связанный с этим сокетом, если он есть.
- `InetAddress getInetAddress()` - Возвращает локальный адрес этого сокета сервера.
- `int getLocalPort()` - Возвращает номер порта, на котором прослушивается этот сокет.
- `SocketAddress getLocalSocketAddress()` - Возвращает адрес конечной точки, к которой привязан этот сокет.
- `int getReceiveBufferSize()` - Получает значение параметра `SO_RCVBUF` для этого `ServerSocket`, то есть предлагаемый размер буфера, который будет использоваться для сокетов, принятых из этого `ServerSocket`.
- `boolean getReuseAddress()` - Проверяет, включен ли `SO_REUSEADDR`.
- `int getSoTimeout()` - Получить настройку для `SO_TIMEOUT`.
- `protected void implAccept(Socket s)` - Подклассы `ServerSocket` используют этот метод, чтобы переопределить accept(), чтобы вернуть свой собственный подкласс сокета.
- `boolean isBound()` - Возвращает состояние привязки `ServerSocket`.
- `boolean  isClosed()` - Возвращает закрытое состояние `ServerSocket`.
- `void setPerformancePreferences(int connectionTime, int latency, int bandwidth)` - Задает параметры производительности для этого `ServerSocket`.
- `void setReceiveBufferSize(int size)` - Устанавливает предлагаемое значение по умолчанию для параметра `SO_RCVBUF` для сокетов, принимаемых из этого `ServerSocket`.
- `void setReuseAddress(boolean on)` - Включить/выключить параметр сокета `SO_REUSEADDR` статическая пустота setSocketFactory (SocketImplFactory fac) Устанавливает фабрику реализации серверных сокетов для           приложения.
- `void setSoTimeout(int timeout)` - Включить/отключить SO_TIMEOUT с указанным временем ожидания в миллисекундах.
- `String toString()` - Возвращает адрес реализации и порт реализации этого сокета в виде строки.

---
**Более подробно см.:** [Class ServerSocket](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html)

---
**Доп. материалы:**
- [ServerSocket.java (GitHub from OpenJDK)](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/net/ServerSocket.java)
- [Writing the Server Side of a Socket](https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html)
- [Socket Programming in Java](https://www.geeksforgeeks.org/java/socket-programming-in-java/)
- [A Simple HTTP Server With Java ServerSocket](https://www.baeldung.com/java-serversocket-simple-http-server)
- [https://developer.android.com/reference/java/net/ServerSocket.html](https://developer.android.com/reference/java/net/ServerSocket.html)
- [Java ServerSocket Simple HTTP Server Example](https://www.javacodegeeks.com/java-serversocket-simple-http-server-example.html)
- [Java ServerSocket Class](https://www.geeksforgeeks.org/java/java-net-serversocket-class-in-java/)
- [Class ServerSocket (from Massachusetts Institute of Technology)](https://web.mit.edu/java_v1.0.2/www/apibook/javaf5.htm)
- [Java Networking: ServerSocket (from jenkov.com)](https://jenkov.com/tutorials/java-networking/server-sockets.html)
- [A Guide to Java Sockets](https://www.baeldung.com/a-guide-to-java-sockets)
- [Java Standard: Socket und ServerSocket (java.net) (from de.wikibooks.org)](https://de.wikibooks.org/wiki/Java_Standard:_Socket_und_ServerSocket_(java.net))
- [Практика использования классов Socket и ServerSocket в Java](https://habr.com/ru/articles/883076/)
