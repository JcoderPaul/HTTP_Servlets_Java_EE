### Данный раздел описывает работу простых Servlets (сервлетов).

- [FirstServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/HttpServlets/src/FirstServlet) - примеры трех вариантов сервлетов с переопределенными методами doGet и doPost.

    - [FirstServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/HttpServlets/src/FirstServlet/FirstServlet.java) - в данном случае переопределен метод doGet, который на простой запрос из браузера возвращает ответ: Hello from first Servlet! 
    - [SecondServletDoPost](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/HttpServlets/src/FirstServlet/SecondServletDoPost.java) - в данном случае переопределен метод doPost, который позволяет возвращать ответ на запрос с параметрами в теле запроса (используем Postman). Так же переопределен метод doGet, который позволяет отправлять параметры через URL, например http://localhost:8080/second?param=25&id=1. В методе добавлены строки, которые позволяют выводить переданные параметры в консоли IDE.  
    - [ThirdServletPostTxt](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/HttpServlets/src/FirstServlet/ThirdServletPostTxt.java) - в данном случае так же используя Postman мы передаем в теле запроса текст, а метод doPost его обрабатывает и возвращает ответ.
- [ReadFileFromNet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/HttpServlets/src/ReadFileFromNet) - примеры простых сервлетов позволяющих скачивать некие файлы.

  - [DownloadServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/HttpServlets/src/ReadFileFromNet/DownloadServlet.java) - в данном случае обращаясь к сервлету пользователь скачает текстовый файл с единственной строкой.
  - [DownloadServletTwo](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/HttpServlets/src/ReadFileFromNet/DownloadServletTwo.java) - в данном случае, как и в предыдущем, мы задали хедеры, тип контента и т.д. Теперь мы скачиваем json файл из папки resources и получаем txt файл. Естественно папка resources помечена как Root.

### Документация и папка ресурсов

- [DOC](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/HttpServlets/DOC) - краткие статьи по JavaEE, Java Servlets и TomCat (описание возможных проблем).
- [resources](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/HttpServlets/resources) - JSON файл для демонстрации его скачивания сервлетом DownloadServletTwo.
