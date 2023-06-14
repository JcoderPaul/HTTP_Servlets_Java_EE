### Данный раздел описывает классические примеры работы с классом Socket.

- [TCP_Sockets](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/TCP_Sockets) - примеры создания простых клиентов и серверов, отправка и получение сообщений, работа протокола TCP.
    
  - [SimpleMessenger](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/TCP_Sockets/SimpleMessenger) - простой приемо-передатчик сообщений на базе клиента и сервера.
  - [SocketClient](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/TCP_Sockets/SocketClient) - пример работы класса Socket и логика построения приложений.
  - [SocketServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/TCP_Sockets/SocketServer) - пример примитивного сервера и демонстрация работы класса ServerSocket.
- [UDP_Datagrams](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/UDP_Datagrams) - пример работы протокола UDP и класса DatagramSocket.
- [URL_Client](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/URL_Client) - пример работы класса URL и URLConnection, удобен для работы с GET - запросами.
  
  - [SimpleURLClient](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/URL_Client/SimpleURLClient.java) - пример отправки запроса на URL в интернете и получение ответа.
  - [UniversalURLClient](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/URL_Client/UniversalURLClient.java) - пример того, что класс URL относительно универсален и с помощью него можно читать содержимое файлов, как в сети, так и на локальной машине.
- [HTTP_Client](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/HTTP_Client) - пример примитивного HTTP клиента, пример работы класса HttpClient и HttpRequest, HttpResponse. Во многих случаях класс HttpClient предпочтителен по сравнению с URL из-за богатства подклассов и методов. Класс HttpClient потокобезопасен и позволяет создать один объект на все приложение, а также отправлять запросы в асинхронном режиме и не дожидаться ответа. 
- [HTTP_SimpleServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/HTTP_SimpleServer) - пример примитивного HTTP сервера принимающего запрос через браузер или Postman.
- [HTTP_SingleThreadServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/HTTP_SingleThreadServer) - однопоточный HTTP сервер и пример его работы. В данном случае HTTP клиент отправляет серверу запрос в виде JSON файла, а сервер отвечает клиенту HTML страницей, все выводится в консоль.
  
  - [FirstHttpServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/HTTP_SingleThreadServer/FirstHttpServer.java) - сервер, который получает запрос и отправляет ответ.
  - [HttpClientTester](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/HTTP_SingleThreadServer/HttpClientTester.java) - простой тестировщик нашего HTTP сервера, идет запрос POST в виде json файла.
- [HTTP_MultiThreadServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/src/HTTP_MultiThreadServer) - в данном примере рассматривается многопоточный вариант HTTP сервера. Для организации режима многопоточности используется ExecutorService.

  - [PoolThreadHttpServer](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/HTTP_MultiThreadServer/PoolThreadHttpServer.java) - многопоточный сервер, очень похож на предыдущую версию, исключением является способность обрабатывать несколько запросов одновременно. Метод *.accept() находится в бесконечном цикле, а работу по обработке запросов выполняет пул потоков.
  - [HttpRequestRunner](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/SocketClasses/src/HTTP_MultiThreadServer/HttpRequestRunner.java) - клиент формирует три асинхронных запроса к серверу, которые обрабатываются сервером (отображение в консоли).

### Документация и примеры форматов

- [DOC](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/DOC) - описание классов применяемых в данном разделе.
- [resources](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/SocketClasses/resources) - примеры XML, JSON и HTML файлов, структура данных файла myFirst.json приведена в соответствие с myFirst.xml файлом.
