- **См. исходник (ENG): [Class URLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html)**

---
### Класс URLConnection

```java
public abstract class URLConnection
                          extends Object
```

**Пакет:** [java.net](https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html)

**Прямые известные подклассы:** 
- [HttpURLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html),
- [JarURLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/JarURLConnection.html)

Абстрактный класс URLConnection является надклассом всех классов, представляющих канал связи между приложением и URL. 
Экземпляры этого класса можно использовать как для чтения, так и для записи в ресурс, на который ссылается URL. Как 
правило, создание подключения к URL — это многоэтапный процесс:

**Методы:**
- **openConnection()** - Управление параметрами, влияющими на подключение к удаленному ресурсу.
- **connect()** - Взаимодействовать с ресурсом; поля и содержимое заголовка запроса.

**Пошаговый процесс:**
*1. Объект подключения создается путем вызова `метода openConnection` для URL.*
*2. Параметры установки и общие свойства запроса настраиваются.*
*3. Фактическое подключение к удаленному объекту осуществляется с помощью `метода connect`.*
*4. Удаленный объект становится доступным. Можно получить доступ к полям заголовка и содержимому удаленного объекта.*

**Параметры настройки изменяются следующими способами (методами):**
- setAllowUserInteraction
- setDoInput
- setDoOutput
- setIfModifiedSince
- setUseCaches

**Общие свойства запроса изменяются с помощью метода:**
- setRequestProperty

Значения по умолчанию для параметров `AllowUserInteraction` и `UseCaches` можно задать с помощью
методов `setDefaultAllowUserInteraction` и `setDefaultUseCaches`.

Каждый из приведенных выше методов `set` имеет соответствующий метод `get` для получения значения параметра или общего 
свойства запроса. Конкретные параметры и общие свойства запроса, которые применимы, зависят от протокола.

**Следующие методы используются для доступа к полям заголовка и содержимому после установления соединения с удаленным объектом:**
- getContent
- getHeaderField
- getInputStream
- getOutputStream

**К некоторым полям заголовка обращаются часто через методы:**
- getContentEncoding
- getContentLength
- getContentType
- getDate
- getExpiration
- getLastModifed

Которые позволяют обеспечить удобный доступ к выбранным полям. Метод getContentType используется методом getContent для определения 
типа удаленного объекта; подклассы могут счесть удобным переопределить метод getContentType.

В общем случае все параметры перед подключением и общие свойства запроса можно игнорировать: параметры перед подключением и свойства 
запроса по умолчанию имеют значения по умолчанию. Для большинства клиентов этого интерфейса есть только два интересных метода: 
`getInputStream` и `getContent`, которые дублируются в классе URL удобными методами.

Дополнительную информацию о свойствах запроса и полях заголовка http-соединения можно найти по: [Hypertext Transfer Protocol -- HTTP/1.1](http://www.ietf.org/rfc/rfc2616.txt)

Необходимо закрывать потоки, вызовом метода `close()` для `InputStream` или `OutputStream`. URLConnection после запроса нужно освободить 
сетевые ресурсы, связанные с этим экземпляром, если только конкретные спецификации протокола не определяют другое поведение для него.

---
**См. так же:** 
- [URL.openConnection()](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html#openConnection--),
- [connect()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#connect--),
- [getContent()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getContent--),
- [getContentEncoding()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getContentEncoding--),
- [getContentLength()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getContentLength--),
- [getContentType()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getContentType--),
- [getDate()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getDate--),
- [getExpiration()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getExpiration--),
- [getHeaderField(int)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getHeaderField-int-),
- [getHeaderField(java.lang.String)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getHeaderField-java.lang.String-),
- [getInputStream()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getInputStream--),
- [getLastModified()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getLastModified--),
- [getOutputStream()](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getOutputStream--),
- [setAllowUserInteraction(boolean)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setAllowUserInteraction-boolean-),
- [setDefaultUseCaches(boolean)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setDefaultUseCaches-boolean-),
- [setDoInput(boolean)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setDoInput-boolean-),
- [setDoOutput(boolean)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setDoOutput-boolean-),
- [setIfModifiedSince(long)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setIfModifiedSince-long-),
- [setRequestProperty(java.lang.String, java.lang.String)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setRequestProperty-java.lang.String-java.lang.String-),
- [setUseCaches(boolean)](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#setUseCaches-boolean-)

---
#### Поля

- `protected boolean allowUserInteraction` - Если `true`, этот URL проверяется в контексте, в котором имеет смысл разрешить взаимодействие с пользователем, например, всплывающее диалоговое окно аутентификации.
- `protected boolean connected` - Если значение равно `false`, этот объект подключения не создал ссылку для связи с указанным URL.
- `protected boolean doInput` - Эта переменная устанавливается методом `setDoInput`.
- `protected boolean doOutput` - Эта переменная устанавливается методом `setDoOutput`.
- `protected long ifModifiedSince` - Некоторые протоколы поддерживают пропуск извлечения объекта, если только объект не был изменен позднее определенного времени.
- `protected URL url` - URL представляет собой удаленный объект во Всемирной паутине, к которому открыто это соединение.
- `protected boolean useCaches` - Если `true`, протоколу разрешено использовать кэширование, когда это возможно.

---
#### Конструктор

- `protected URLConnection(URL url)` - Создает URL-соединение с указанным URL.

Метод `openConnection()` возвращает `java.net.URLConnection`, абстрактный класс, подклассы которого представляют различные типы подключений URL.

**Например:**
Если мы подключаемся к URL, протокол которого HTTP, метод openConnection() возвращает объект подключения HttpURL. Если мы подключаемся к URL, 
который представляет файл JAR (архивный файл приложения на языке Java), метод openConnection() возвращает объект подключения JarURL и т.д.

У класса подключения URL есть много методов для установки или определения информации о соединении, включая следующие:

---
#### Методы

- `void addRequestProperty(String key, String value)` - Добавляет общее свойство запроса, указанное парой ключ-значение.
- `abstract void connect()` - Открывает ссылку для связи с ресурсом, на который ссылается этот URL, если такое соединение еще не установлено.
- `boolean getAllowUserInteraction()` - Возвращает значение поля allowUserInteraction для этого объекта.
- `int getConnectTimeout()` - Возвращает настройку времени ожидания подключения.
- `Object getContent()` - Извлекает содержимое этого URL-соединения.
- `Object getContent(Class[] classes)` - Извлекает содержимое этого URL-соединения.
- `String getContentEncoding()` - Возвращает значение поля заголовка кодировки содержимого.
- `int getContentLength()` - Возвращает значение поля заголовка длины содержимого.
- `long getContentLengthLong()` - Возвращает значение поля заголовка длины содержимого как длинное.
- `String getContentType()` - Возвращает значение поля заголовка типа содержимого.
- `long getDate()` - Возвращает значение поля заголовка даты.
- `static boolean getDefaultAllowUserInteraction()` - Возвращает значение по умолчанию для поля allowUserInteraction.
- `static String getDefaultRequestProperty(String key)` - Deprecated. - Метод getRequestProperty для конкретного экземпляра следует использовать после получения соответствующего экземпляра URLConnection.
- `boolean getDefaultUseCaches()` - Возвращает значение по умолчанию для флага useCaches URLConnection.
- `boolean getDoInput()` - Возвращает значение флага doInput этого URLConnection.
- `boolean getDoOutput()` - Возвращает значение флага doOutput этого URLConnection.
- `long getExpiration()` - Возвращает значение поля заголовка expires.
- `static FileNameMap getFileNameMap()` - Загружает карту имен файлов (mime-table) из файла данных.
- `String getHeaderField(int n)` - Возвращает значение для n-го поля заголовка.
- `String getHeaderField(String name)` - Возвращает значение именованного поля заголовка.
- `long getHeaderFieldDate(String name, long Default)` - Возвращает значение именованного поля, проанализированное как дата.
- `int getHeaderFieldInt(String name, int Default)` - Возвращает значение именованного поля, проанализированное как число.
- `String getHeaderFieldKey(int n)` - Возвращает ключ для n-го поля заголовка.
- `long getHeaderFieldLong(String name, long Default)` - Возвращает значение именованного поля, проанализированное как число.
- `Map<String,List<String>> getHeaderFields()` - Возвращает неизменяемую карту полей заголовка.
- `long getIfModifiedSince()` - Возвращает значение поля ifModifiedSince этого объекта.
- `InputStream getInputStream()` - Возвращает входной поток, который считывается из этого открытого соединения.
- `long getLastModified()` - Возвращает значение последнего модифицированного поля заголовка.
- `OutputStream getOutputStream()` - Возвращает выходной поток, который записывает в это соединение.
- `Permission getPermission()` - Возвращает объект разрешения, представляющий разрешение, необходимое для установления соединения, представленного этим объектом.
- `int getReadTimeout()` - Возвращает настройку тайм-аута чтения.
- `Map<String,List<String>> getRequestProperties()` - Возвращает неизменяемую карту общих свойств запроса для этого соединения.
- `String getRequestProperty(String key)` - Возвращает значение именованного общего свойства запроса для этого соединения.
- `URL getURL()` - Возвращает значение поля URL этого URLConnection.
- `boolean getUseCaches()` - Возвращает значение поля useCaches этого URLConnection.
- `static String guessContentTypeFromName(String fname)` - Пытается определить тип содержимого объекта на основе указанного "файлового" компонента URL.
- `static String guessContentTypeFromStream(InputStream is)` - Пытается определить тип входного потока на основе символов в начале входного потока.
- `void setAllowUserInteraction(boolean allowuserinteraction)` - Устанавливает значение поля allowUserInteraction этого URLConnection.
- `void setConnectTimeout(int timeout)` - Задает указанное значение тайм-аута в миллисекундах, которое будет использоваться при открытии канала связи с ресурсом, на который ссылается этот URLConnection.
- `static void	setContentHandlerFactory(ContentHandlerFactory fac)` - Задает ContentHandlerFactory приложения.
- `static void	setDefaultAllowUserInteraction(boolean defaultallowuserinteraction)` - Устанавливает значение по умолчанию для поля allowUserInteraction для всех будущих объектов URLConnection на указанное значение.
- `static void	setDefaultRequestProperty(String key, String value)` - Deprecated. - Метод setRequestProperty для конкретного экземпляра следует использовать после получения соответствующего экземпляра URLConnection. Вызов этого метода не будет иметь никакого эффекта.
- `void setDefaultUseCaches(boolean defaultusecaches)` - Устанавливает значение по умолчанию для поля useCaches на указанное значение.
- `void setDoInput(boolean doinput)` - Устанавливает значение поля doInput для этого URLConnection в указанное значение. Если true (истина) - обозначаем, что соединение будет использоваться для ввода. Значение по умолчанию - true, потому что клиенты обычно считывают из URL-соединения.
- `void setDoOutput(boolean dooutput)` - Устанавливает значение поля doOutput для этого URLConnection в указанное значение. Если задать true, что означает - соединение будет использоваться для вывода. Значение по умолчанию - false (ложь), поскольку многие типы URL не поддерживают запись.
- `static void	setFileNameMap(FileNameMap map)` - Задает FileNameMap.
- `void setIfModifiedSince(long ifmodifiedsince)` - Устанавливает значение поля ifModifiedSince этого URLConnection в указанное значение.
- `void setReadTimeout(int timeout)` - Устанавливает тайм-аут чтения на указанный тайм-аут в миллисекундах.
- `void setRequestProperty(String key, String value)` - Задает общее свойство запроса.
- `void setUseCaches(boolean usecaches)` - Устанавливает значение поля useCaches этого URLConnection в указанное значение.
- `String toString()` - Возвращает строковое представление этого URL-подключения.

---
#### Пример кода соединения с HTTP

Следующая программа HttpURLConnection подключается к URL-адресу, заданному в теле самой программы через конструктор. Похожие примеры
можно найти в файлах описаниях:
- [URLConnection_Methods.md](../DOC/URLConnection_Methods.md)
- [URLConnection_Read_Write.md](../DOC/URLConnection_Read_Write.md)

Если URL представляет ресурс HTTP, соединение преобразуется в HttpURL-соединение, и данные в ресурсе считываются по одной строке за раз.

```java
  import java.net.*;
  import java.io.*;
  
  public class HttpURLConnection {
  
     public static void main(String [] args) {
        try {
           URL url = new URL("https://www.yandex.ru");
           URLConnection urlConnection = url.openConnection();
           HttpURLConnection connection = null;
           if(urlConnection instanceof HttpURLConnection) {
              connection = (HttpURLConnection) urlConnection;
           }else {
              System.out.println("Пожалуйста, введите HTTP URL.");
              return;
           }
  
           BufferedReader in = new BufferedReader(
              new InputStreamReader(connection.getInputStream()));
           String urlString = "";
           String current;
  
           while((current = in.readLine()) != null) {
              urlString += current;
           }
           System.out.println(urlString);
        } catch (IOException e) {
           e.printStackTrace();
        }
     }
  }
```

---
**Подробное описание см. (ENG):** [URLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html)

---
**См. доп.:**
- [Reading from and Writing to a URLConnection (from ORACLE Java Tutorial)](https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html)
- [Java - Reading From a URL using URLConnection Class](https://www.geeksforgeeks.org/java/java-reading-from-a-url-using-urlconnection-class/)
- [UrlHttpRequestExample.java (from GitHub)](https://gist.github.com/thomasdarimont/20e8a58f35ae3181473991f1d1e9a174)
- [Java - HttpURLConnection Class](https://www.tutorialspoint.com/java/java_httpurlconnection.htm)
- [Java URLConnection and HttpURLConnection Examples](https://www.codejava.net/java-se/networking/java-urlconnection-and-httpurlconnection-examples)
- [java.net.URLConnection Class in Java](https://www.geeksforgeeks.org/java/java-net-urlconnection-class-in-java/)
- [Reading from and Writing to a URLConnection (from Indian Institute of Technology Kanpur)](https://www.iitk.ac.in/esc101/05Aug/tutorial/networking/urls/readingWriting.html)
- [ava Networking - Using HttpURLConnection to download files from the Internet - Tutorial](https://www.vogella.com/tutorials/JavaNetworking/article.html)
- [Mock a URL Connection in Java](https://www.baeldung.com/java-simulate-url-connection)
- [Java Networking: URL + URLConnection](https://jenkov.com/tutorials/java-networking/url-urlconnection.html)
