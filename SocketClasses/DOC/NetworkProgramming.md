### Сетевое программирование

Термин «сетевое программирование» относится к написанию программ, которые выполняются на нескольких устройствах (компьютерах), 
в которых все устройства подключены друг к другу с помощью сети.

Пакет [java.net](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/package-summary.html) API-интерфейсов J2SE содержит набор классов и интерфейсов,
предоставляющих детали низкоуровневого взаимодействия, что позволяет писать программы, ориентированные на решение поставленной задачи.

Пакет [java.net](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/package-summary.html) обеспечивает поддержку двух распространенных сетевых протоколов:
- **TCP** — TCP протокол управления передачей данных (запрос - ответ, с подтверждением получения пакета данных), который обеспечивает надежную связь между двумя приложениями. 
TCP обычно используется через Интернет-протокол, который называется - TCP/IP.
- **UDP** — UDP означает протокол пользовательских дейтаграмм, протокол без установления соединения (запрос, без подтверждения получения пакета данных), который позволяет
передавать пакеты данных между приложениями.

---
#### Программирование сокетов.

Сокеты обеспечивают механизм связи между двумя компьютерами с использованием TCP. Клиентская программа создает сокет на своем конце соединения и пытается соединить
этот сокет с сервером.

Когда соединение установлено, сервер создает объект сокета на своем конце соединения. Теперь клиент и сервер могут взаимодействовать, записывая и читая из сокета.

[Класс java.net.Socket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/Socket.html) представляет собой сокет, а [класс java.net.ServerSocket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/ServerSocket.html) предоставляет серверной программе механизм прослушивания клиентов и установления
с ними соединений.

**Следующие шаги выполняются при установлении TCP-соединения между двумя компьютерами с использованием сокетов:**
**1. Сервер создает объект ServerSocket**, указывающий, на каком номере порта должна происходить связь.
**2. Сервер вызывает метод `accept()` класса `ServerSocket`.** Этот метод ожидает, пока клиент не подключится к серверу через заданный порт.
**3. Пока сервер ожидает соединения с ним, клиент создает экземпляр объекта Socket**, указывая имя сервера и номер порта для подключения.

Конструктор класса Socket пытается подключить клиента к указанному серверу и номеру порта. Если связь установлена, у клиента теперь есть объект Socket, способный
взаимодействовать с сервером.

На стороне сервера метод `accept()` возвращает ссылку на новый сокет на сервере, который подключен к сокету клиента.

**После того как соединения установлены, обмен данными может осуществляться с использованием потоков ввода/вывода.** Каждый сокет имеет как [OutputStream](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/io/OutputStream.html), так и [InputStream](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/io/InputStream.html). 
Поток вывода клиента подключен к потоку ввода сервера, а поток ввода клиента подключен к потоку вывода сервера.

**TCP — это протокол двусторонней связи**, поэтому данные могут передаваться по обоим потокам одновременно. Ниже приведены полезные классы, предоставляющие
полный набор методов для реализации сокетов.

---
#### Методы класса ServerSocket

[Класс java.net.ServerSocket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/ServerSocket.html) используется серверными приложениями для получения
порта и прослушивания клиентских запросов.

**Класс [ServerSocket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/ServerSocket.html) имеет четыре конструктора:**

- `public ServerSocket(int port) throws IOException`: Пытается создать серверный сокет, привязанный к указанному порту. Исключение возникает, если порт уже
привязан к другому приложению.
- `public ServerSocket(int port, int backlog) throws IOException`: Работает, как и предыдущий конструктор. Параметр backlog указывает, сколько входящих клиентов
следует хранить в очереди ожидания.
- `public ServerSocket(int port, int backlog, InetAddress address) throws IOException`: Как и первые два конструктора, а так же параметр InetAddress, который указывает
локальный IP-адрес для привязки. InetAddress используется для серверов, которые могут иметь несколько IP-адресов, что позволяет серверу указывать, на каком из его
IP-адресов принимать клиентские запросы.
- `public ServerSocket() throws IOException`: Создает несвязанный серверный сокет. При использовании этого конструктора используйте метод bind(), когда мы готовы
привязать сокет сервера.

Если конструктор ServerSocket не выкинул исключение, это значит, что наше приложение успешно выполнило привязку к указанному порту и готово к клиентским запросам.

**Ниже приведены некоторые из распространенных методов класса ServerSocket:**

- `public int getLocalPort()`: Возвращает порт, который прослушивает серверный сокет. Этот метод полезен, если мы передали 0 в качестве номера порта в конструкторе и
позволили серверу найти порт самостоятельно.
- `public Socket accept() throws IOException`: Ожидает входящего клиента. Этот метод блокируется до тех пор, пока либо клиент не подключится к серверу через указанный
порт, либо время ожидания сокета не истечет, при условии, что значение времени ожидания было установлено с помощью метода setSoTimeout(). В противном случае этот
метод блокируется на неопределенный срок.
- `public void setSoTimeout(int timeout)`: Устанавливает значение тайм-аута, в течение которого сокет сервера ожидает клиента во время accept().
- `public void bind(SocketAddress host, int backlog)`: Привязывает сокет к указанному серверу и порту в объекте SocketAddress. Использовать этот метод, если мы создали
экземпляр ServerSocket с помощью конструктора без аргументов.

Когда ServerSocket вызывает accept(), метод ожидает до тех пор, пока не подключится клиент. После того, как клиент подключился, ServerSocket создает новый сокет на
неуказанном порту и возвращает ссылку на этот новый сокет. Теперь между клиентом и сервером существует TCP-соединение, и можно начинать обмен данными.

---
#### Методы класса Socket

Класс [java.net.Socket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/Socket.html) представляет сокет, который клиент и сервер используют для
связи друг с другом. Клиент получает объект Socket, создавая экземпляр, тогда как сервер получает объект Socket из возвращаемого значения метода accept().

**Класс Socket имеет пять конструкторов, которые клиент использует для подключения к серверу:**

- `public Socket(String host, int port) throws UnknownHostException, IOException`: Сокет пытается подключиться к указанному серверу host через указанный порт port.
Если этот конструктор не выбрасывает исключение, соединение установлено успешно, и клиент подключается к серверу.
- `public Socket(InetAddress host, int port) throws IOException`: Этот метод идентичен предыдущему конструктору, за исключением того, что хост обозначается объектом
InetAddress.
- `public Socket(String host, int port, InetAddress localAddress, int localPort) throws IOException`: Подключается к указанному хосту и порту, создавая сокет на локальном хосте
по указанному адресу и порту.
- `public Socket(InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException`: Этот метод идентичен предыдущему конструктору, за исключением того,
что хост обозначается объектом InetAddress вместо String.
- `public Socket()`: Создает несвязанный сокет. Используйте метод connect() для подключения этого сокета к серверу.

Когда конструктор [Socket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/Socket.html) возвращается, он не просто создает экземпляр объекта Socket, 
а фактически пытается подключиться к указанному серверу и порту.

Ниже перечислены некоторые методы класса [Socket](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/Socket.html). Нужно помнить, что и клиент, и сервер 
имеют объект Socket, поэтому эти методы могут вызываться как клиентом, так и сервером.

- `public void connect(SocketAddress host, int timeout) throws IOException`: Этот метод подключает сокет к указанному хосту. Этот метод необходим только при создании экземпляра
Socket с помощью конструктора без аргументов.
- `public InetAddress getInetAddress()`: Этот метод возвращает адрес другого компьютера, к которому подключен этот сокет.
- `public int getPort()`: Возвращает порт, к которому привязан сокет на удаленной машине.
- `public int getLocalPort()`: Возвращает порт, к которому привязан сокет на локальном компьютере.
- `public SocketAddress getRemoteSocketAddress()`: Возвращает адрес удаленного сокета.
- `public InputStream getInputStream() throws IOException`: Возвращает входной поток сокета. Входной поток подключается к выходному потоку удаленного сокета.
- `public OutputStream getOutputStream() throws IOException`: Возвращает выходной поток сокета. Выходной поток подключается к входному потоку удаленного сокета.
- `public void close() throws IOException`: Закрывает сокет, из-за чего этот объект Socket больше не может снова подключаться к какому-либо серверу.

---
#### Методы класса InetAddress

[Этот класс](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/InetAddress.html) представляет адрес интернет-протокола (IP). Полезные методы класса, 
которые понадобятся при программировании сокетов:

- `static InetAddress getByAddress(byte[] addr)`: Возвращает объект InetAddress с заданным необработанным IP-адресом.
- `static InetAddress getByAddress(String host, byte[] addr)`: Создает InetAddress на основе предоставленного имени хоста и IP-адреса.
- `static InetAddress getByName(String host)`: Определяет IP-адрес хоста по имени хоста.
- `String getHostAddress()`: Возвращает строку IP-адреса в текстовом представлении.
- `String getHostName()`: Получает имя хоста для заданного IP-адреса.
- `static InetAddress InetAddress getLocalHost()`: Возвращает локальный хост.
- `String toString()`: Преобразует заданный/полученный IP-адрес в строку.

---
#### Пример сокет-клиента

Клиентская программа, подключается к серверу с помощью сокета и отправляет запрос (request)- приветствие, а затем ожидает response - ответа. 
Поскольку это некая демонстрация, то мы программу реализующую сервер развернем на локальной машине. И ее адрес будет localhost/127.0.0.1 порт 
для сервера выберем 7777.

```java
   import java.net.*;
   import java.io.*;
   
   public class SocketClient {
   
      public static void main(String [] args) {
         // Первым аргументом передаем адрес сервера localhost
         String serverName = args[0];
         // Вторым аргументом передаем порт, например 7777
         int port = Integer.parseInt(args[1]);
         // В данном примере не используется блок try-with-recourse
         try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
   
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
   
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
   
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
   
            // Закрываем клиентский сокет
            client.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
```

---
#### Пример сокет - сервера

Программа примером серверного приложения, которое использует класс Socket для прослушивания клиентов на указанном номере порта. 
Порт можно задать в самой программе, а можно указать аргументом командной строки. В данном случае работа происходит в отдельном потоке. 
Поскольку мы создаем сервер на локальной машине, то ее IP или имя и будет адресом на котором работает наш сервер, и к этому адресу 
будет обращаться наш клиент, как это выше и указано - (localhost), чтобы клиент смог обратиться к нашему серверу порт выберем 7777 и 
его же укажем при запуске клиентского приложения.

```java
   import java.net.*;
   import java.io.*;
   
   // Наследуем наш сервер от класса Thread
   public class SocketServerRunner extends Thread {
      private ServerSocket serverSocket;
   
      public SocketServerRunner(int port) throws IOException {
         serverSocket = new ServerSocket(port);
         // Задаем время ожидания открытого порта
         serverSocket.setSoTimeout(10000);
      }
   
      public static void main(String [] args) {
               //Получаем номер порта из аргументов
               int port = Integer.parseInt(args[0]);
               try {
                  // Создаем отдельный поток и запускаем сервер в нем
                  Thread t = new SocketServerRunner(port);
                  t.start();
               } catch (IOException e) {
                  e.printStackTrace();
               }
      }
   
      public void run() {
         while(true) {
            try {
               System.out.println("Waiting for client on port " +
                  serverSocket.getLocalPort() + "...");
               Socket server = serverSocket.accept();
   
               System.out.println("Just connected to " + server.getRemoteSocketAddress());
               DataInputStream in = new DataInputStream(server.getInputStream());
               System.out.println(in.readUTF());
   
               DataOutputStream out = new DataOutputStream(server.getOutputStream());
               out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                  + "\nGoodbye!");
   
               server.close();
   
            } catch (SocketTimeoutException s) {
               System.out.println("Socket timed out!");
               break;
            } catch (IOException e) {
               e.printStackTrace();
               break;
            }
         }
      }
   }
```

Пример работы (без выделенного потока) приведен в разделе [TCP_Sockets](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/TCP_Sockets). 
В нашем случае, сначала нужно запустить сервер, а затем клиентскую программу, т.к. в противном случае клиентский модуль выбросит исключение не найдя слушающий сервер.

---
**Доп. материал:**
- [Java ServerSocket Class](https://www.geeksforgeeks.org/java/java-net-serversocket-class-in-java/)
- [Class ServerSocket (ORACLE Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/ServerSocket.html)
- [Introduction to Socket Programming in Java](https://medium.com/@vsjathu2000/introduction-to-socket-programming-in-java-1843cba6dad8)
- [A Guide to Java Sockets](https://www.baeldung.com/a-guide-to-java-sockets)
- [Programming Servers Using Sockets (from Saint Mary's University)](https://cs.smu.ca/~porter/csc/465/notes/net/servers.html)
- [Socket Class and ServerSocket Class in Java Networking](https://www.c-sharpcorner.com/article/socket-class-and-serversocket-class-in-java-networking/)
- [What is the difference between Socket and ServerSocket? (from stackoverflow.com)](https://stackoverflow.com/questions/2004531/what-is-the-difference-between-socket-and-serversocket)
- [A Simple HTTP Server With Java ServerSocket](https://www.baeldung.com/java-serversocket-simple-http-server)
- [Классы Socket и ServerSocket в Java (from JavaRush.com)](https://javarush.com/groups/posts/654-klassih-socket-i-serversocket-ili-allo-server-tih-menja-slihshishjh)
- [Socket Programming in Java](https://www.geeksforgeeks.org/java/socket-programming-in-java/)

---
- [Class Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html)
- [Lesson: All About Sockets (from ORACLE TUTORIALS)](https://docs.oracle.com/javase/tutorial/networking/sockets/index.html)
- [java.net.Socket Class in Java](https://www.geeksforgeeks.org/java/java-net-socket-class-in-java/)
- [Java Sockets Tutorial (from Northeastern University)](https://www.khoury.northeastern.edu/home/kenb/tutorial/sockets.html)
- [What Is a Socket? (from University of Auckland)](https://www.cs.auckland.ac.nz/references/java/java1.5/tutorial/networking/sockets/definition.html)
- [Simple chat using sockets in Java](https://dev.to/saverio683/sockets-in-java-p2d)
- [Socket Programming in Java: Comprehensive Guide](https://www.boardinfinity.com/blog/socket-programming-in-java-comprehensive-guide/)
- [Know all about Socket Programming in Java](https://www.edureka.co/blog/socket-programming-in-java/)
- [Uses of Class java.net.Socket](https://cr.openjdk.org/~hannesw/8350920/api.00/java.base/java/net/class-use/Socket.html)
- [How to instantiate a Socket class in Java?](https://stackoverflow.com/questions/2303428/how-to-instantiate-a-socket-class-in-java)
- [What is Socket Programming in Java?](https://medium.com/@sarathkumar52356/what-is-socket-programming-in-java-11216d5d56ac)
- [Java Socket Programming – Upgrade your programming skills in Java](https://techvidvan.com/tutorials/java-socket-programming/)

---
- [InetAddress.java (GitHub from OpenJDK)](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/net/InetAddress.java)
- [Class InetAddress (ORACLE Java 8)](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)
- [InetAddress Class in Java](https://www.geeksforgeeks.org/java/inetaddress-class-in-java/)
- [java.net.InetAddress Class in Java](https://www.geeksforgeeks.org/java/java-net-inetaddress-class-in-java/)
- [InetAddress in Java](https://www.scaler.com/topics/inetaddress-in-java/)
- [Java InetAddress Examples](https://www.codejava.net/java-se/networking/java-inetaddress-examples)
- [Java InetAddress Class](https://www.tpointtech.com/inetaddress-class)
- [InetAddress - Java](https://www.brainkart.com/article/InetAddress---Java_10614/)
