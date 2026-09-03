- **См. исходник (ENG): [Class URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html)**

---
### Класс URL

**Пакеты:** [java.net](https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html)

**Все реализованные интерфейсы:** [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)

```java
public final class URL
  extends Object
    implements Serializable
```

Класс URL представляет унифицированный указатель ресурса, указатель на «ресурс» во всемирной паутине. 
Ресурс может быть чем-то простым, например, файлом или каталогом, или ссылкой на более сложный объект, 
например запрос к базе данных или поисковой системе.

Более подробную информацию о типах URL-адресов и их форматах можно найти по ссылке: [Типы URL-адресов](https://web.archive.org/web/20051210035621/http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html).

URL может быть разбит на части, как указано ниже:

```
  http://www.example.com/docs/resource1.html
```

Приведенный выше URL-адрес указывает, что следует использовать протокол `http(протокол передачи гипертекста)` 
и, что информация находится на хост-компьютере с именем `www.example.com`. Информация об этом хост-компьютере 
называется `/docs/resource1.html`. Точное значение этого имени на хост-компьютере зависит как от протокола, так
и от хоста. Информация обычно находится в файле, но может быть сгенерирована на лету. Этот компонент URL 
называется компонентом пути.

URL-адрес может дополнительно указывать «порт», который представляет собой номер порта, к которому устанавливается 
TCP-соединение на удаленном хост-компьютере. Если порт не указан, вместо него используется порт по умолчанию для 
протокола. Например, **порт по умолчанию для http - 80**. 

**Альтернативный порт может быть указан как:**

```
  http://www.example.com:1080/docs/resource1.html
```

**К URL-адресу может быть добавлен «фрагмент», также известный как «ссылка».** Фрагмент обозначается знаком `#`, за 
которым следуют другие символы, пример:

```
  http://java.sun.com/index.html#chapter1
```

**Этот фрагмент технически не является частью URL. Скорее это указывает на то, что после извлечения указанного ресурса 
приложение особенно заинтересовано в той части документа, к которой прикреплен тег `chapter1`.** Значение тега зависит 
от ресурса.

Приложение также может указать «относительный URL-адрес», который содержит только информацию, достаточную для доступа к 
ресурсу относительно другого URL-адреса. **Относительные URL-адреса часто используются на HTML-страницах.**

**Например, если содержимое URL:**

```
  http://java.sun.com/index.html
```

Содержащийся в нем относительный URL:

```
  FAQ.html
```

Может быть сокращен до:

```
  http://java.sun.com/FAQ.html
```

Относительный URL-адрес не обязательно должен указывать все компоненты URL-адреса. Если протокол, имя хоста или номер порта 
отсутствуют, значение наследуется от полностью указанного URL-адреса. Файловый компонент должен быть указан. Необязательный 
фрагмент не наследуется.

Класс URL сам по себе не кодирует и не декодирует какие-либо компоненты URL в соответствии с механизмом экранирования, 
определенным в [RFC2396](https://www.rfc-editor.org/rfc/rfc2396.txt). **Вызывающая сторона несет ответственность за кодирование
любых полей, которые необходимо экранировать перед вызовом URL, а также за декодирование любых экранированных полей, которые
возвращаются из URL.**

**Кроме того, поскольку URL-адрес не знает об экранировании URL-адресов, он не распознает эквивалентность между закодированной 
или декодированной формой одного и того же URL-адреса.**

Например, два URL:

```
  http://foo.com/hello world/
```
И

```
  http://foo.com/hello%20world
```

Будут считаться не равными друг другу.

Обратите внимание, что URI при определенных обстоятельствах класс выполняет экранирование полей своих компонентов. Рекомендуемым 
способом управления кодированием и декодированием URL-адресов является использование URI и преобразование между этими двумя классами 
с помощью `toURI()` и `URI.toURL()`.

---
#### Конструкторы

- `URL(String spec)` - Создает объект URL из представления String.
- `URL(String protocol, String host, int port, String file)` - Создает объект URL из указанного протокола, хоста, номера порта и файла.
- `URL(String protocol, String host, int port, String file, URLStreamHandler handler)` - Создает объект URL из указанного протокола, хоста, номера порта, файла и обработчика.
- `URL(String protocol, String host, String file)` - Создает URL-адрес из указанного имени протокола, имени хоста и имени файла.
- `URL(URL context, String spec)` - Создает URL-адрес, анализируя данную спецификацию в указанном контексте.
- `URL(URL context, String spec, URLStreamHandler handler)` - Создает URL-адрес, анализируя данную спецификацию с помощью указанного обработчика в указанном контексте.

#### Методы

- `boolean equals(Object obj)` - Сравнивает этот URL на равенство с другим объектом.
- `String getAuthority()` - Получает ауторити часть этого URL-адреса.
- `Object getContent()` - Получает содержимое этого URL.
- `Object getContent(Class[] classes)` - Получает содержимое этого URL.
- `int getDefaultPort()` - Получает номер порта по умолчанию для протокола, связанного с этим URL-адресом.
- `String getFile()` - Получает имя файла этого URL.
- `String getHost()` - Получает имя узла этого URL-адреса, если применимо.
- `String getPath()` - Получает часть пути этого URL-адреса.
- `int getPort()` - Получает номер порта этого URL-адреса.
- `String getProtocol()` - Получает имя протокола этого URL-адреса.
- `String getQuery()` - Получает часть запроса этого URL.
- `String getRef()` - Получает привязку (также известную как «ссылка») этого URL-адреса.
- `String getUserInfo()` - Получает часть userInfo этого URL-адреса.
- `int hashCode()` - Создает целое число, подходящее для индексации хэш-таблицы.
- `URLConnection openConnection()` - Возвращает экземпляр URLConnection, представляющий подключение к удаленному объекту, на который ссылается URL-адрес.
- `URLConnection openConnection(Proxy proxy)` - То же, что и openConnection(), за исключением того, что соединение будет осуществляться через указанный прокси; Обработчики протоколов, которые не поддерживают проксирование, игнорируют параметр прокси и устанавливают обычное соединение.
- `InputStream openStream()` - Открывает соединение с этим URL-адресом и возвращает InputStream для чтения из этого соединения.
- `boolean sameFile(URL other)` - Сравнивает два URL-адреса, исключая компонент фрагмента.
- `static void	setURLStreamHandlerFactory(URLStreamHandlerFactory fac)` - Задает URLStreamHandlerFactory приложения.
- `String toExternalForm()` - Создает строковое представление этого URL.
- `String toString()` - Создает строковое представление этого URL.
- `URI toURI()` - Возвращает URI, эквивалентный этому URL.

**Пример:**

Следующая программа URLDemo демонстрирует различные части URL.

```java
  import java.net.*;
  import java.io.*;
  
  public class URLDemo {
  
     public static void main(String [] args) {
        try {
           // Для тестирования кода можно использовать другие адреса
           URL url = new URL("https://www.amrood.com/index.htm?language=en#j2se");
  
           System.out.println("URL: " + url.toString());
           System.out.println("протокол: " + url.getProtocol());
           System.out.println("полномочия: " + url.getAuthority());
           System.out.println("имя файла: " + url.getFile());
           System.out.println("хост: " + url.getHost());
           System.out.println("путь: " + url.getPath());
           System.out.println("порт: " + url.getPort());
           System.out.println("порт по умолчанию: " + url.getDefaultPort());
           System.out.println("запрос: " + url.getQuery());
           System.out.println("ссылка: " + url.getRef());
        } catch (IOException e) {
           e.printStackTrace();
        }
     }
  }
```

**Более подробно см.:**
- [Class URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html)
- [Class URI](https://docs.oracle.com/javase/8/docs/api/java/net/URI.html)
- [Class URLEncoder](https://docs.oracle.com/javase/8/docs/api/java/net/URLEncoder.html)
- [Class URLDecoder](https://docs.oracle.com/javase/8/docs/api/java/net/URLDecoder.html)

**Доп. материалы:**
- [A Guide to the Java URL](https://www.baeldung.com/java-url)
- [Java URL Class](https://www.geeksforgeeks.org/java/url-class-java-examples/)
- [Uses of Class java.net.URL](https://docs.oracle.com/en/java/javase/25/docs//api/java.base/java/net/class-use/URL.html)
- [URL.java (GitHub from OpenJDK)](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/net/URL.java)
- [Creating a URL](https://enos.itcollege.ee/~jpoial/docs/tutorial/networking/urls/creatingUrls.html)
- [Learn Java URL Class With Examples](https://data-flair.training/blogs/java-url-class/)
- [How to use URLClassLoader to load a *.class file?](https://stackoverflow.com/questions/738393/how-to-use-urlclassloader-to-load-a-class-file)
- [java.net.URL Class in Java](https://www.geeksforgeeks.org/java/java-net-url-class-in-java/)
- [Java URL Class](https://www.educba.com/java-url-class/)
- [Java - URL Class](https://www.tutorialspoint.com/java/java_url.htm)
