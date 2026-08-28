- **См. офф. док.: [Annotation Type WebServlet](https://jakarta.ee/specifications/servlet/4.0/apidocs/javax/servlet/annotation/webservlet)**

### Annotation Type WebServlet

```java
  @Target(value=TYPE)
  @Retention(value=RUNTIME)
  @Documented
  public @interface WebServlet
```

**Библиотека:** 
- [jakarta.servlet.annotation](https://jakarta.ee/specifications/servlet/4.0/apidocs/javax/servlet/annotation/package-summary)
- [javax.servlet.annotation](https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/package-summary.html)

Аннотация, используемая для объявления сервлета.

Эта аннотация обрабатывается веб контейнером во время развертывания, и
соответствующий сервлет становится доступным по указанным шаблонам URL.

**См. так же:** [Interface Servlet](https://jakarta.ee/specifications/servlet/4.0/apidocs/javax/servlet/servlet)

---
#### ОПЦИОНАЛЬНЫЕ ПАРАМЕТРЫ

- `boolean	asyncSupported` - Указывает, поддерживает ли сервлет асинхронный режим работы.
- `String description` - Описание сервлета
- `String displayName` - Отображаемое имя сервлета
- `WebInitParam[] initParams` - Параметры инициализации сервлета
- `String largeIcon` - Большая иконка сервлета
- `int loadOnStartup` - Порядок загрузки сервлета при запуске
- `String name` - Имя сервлета
- `String smallIcon` - Маленькая иконка сервлета
- `String[] urlPatterns` - Шаблоны URL сервлета
- `String[] value` - Шаблоны URL сервлета

---
### СИНТАКСИС АННОТЦИИ @WebServlet

```java
  @WebServlet(
      attribute1=value1,
      attribute2=value2,
      ...
  )
  public class TheServlet extends HttpServlet {
      // servlet code...
  }
```

**ПРИМЕЧАНИЕ:** атрибуты displayName, description, smallIcon и largeIcon в основном
используются инструментами, IDE или контейнерами сервлетов, они не влияют на
работу сервлета.

---
### ПРИМЕРЫ АННОТАЦИИ @WebServlet

---
#### Сервлет аннотируется только шаблоном URL:

```java
  import java.io.IOException;
  
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  /*
  * или
  * import jakarta.servlet.annotation.WebServlet;
  * import jakarta.servlet.http.HttpServlet;
  * import jakarta.servlet.http.HttpServletRequest;
  * import jakarta.servlet.http.HttpServletResponse;
  */
  
  @WebServlet("/processForm")
  public class MyServlet extends HttpServlet {
      public void doGet(HttpServletRequest request, HttpServletResponse response)
              throws IOException {
          response.getWriter().println("Hello");
      }
  }
```

Здесь сервлет MyServlet сопоставляется с шаблоном URL '/processForm'. При доступе
к этому сервлету он вернет сообщение «Hello».

---
#### Сервлет аннотируется несколькими шаблонами URL:

```java
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;

  @WebServlet(urlPatterns = {"/sendFile", "/uploadFile"})
  public class UploadServlet extends HttpServlet {
      // implement servlet doPost() and doGet()...
  }
```

Здесь к сервлету UploadServlet можно получить доступ через два шаблона URL: `/sendFile` и `/uploadFile`.

#### Объявляем сервлет с дополнительной информацией:

```java
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  
  @WebServlet(
          name = "MyOwnServlet",
          description = "This is my first annotated servlet",
          urlPatterns = "/processServlet"
  )
  public class MyServlet extends HttpServlet {
      // implement servlet doPost() and doGet()...
  }
```

Здесь мы указываем имя и описание класса сервлета MyServlet.

---
#### Объявляем сервлет с некоторыми параметрами инициализации:

```java
  import java.io.IOException;
  import java.io.PrintWriter;
  
  import javax.servlet.annotation.WebInitParam;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  @WebServlet(
          urlPatterns = "/imageUpload",
          initParams =
          {
              @WebInitParam(name = "saveDir", value = "D:/FileUpload"),
              @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,gif,png")
          }
  )
  public class ImageUploadServlet extends HttpServlet {
  
      public void doGet(HttpServletRequest request,
                        HttpServletResponse response)
                                          throws IOException {
          String saveDir = getInitParameter("saveDir");
          String fileTypes = getInitParameter("allowedTypes");
  
          PrintWriter writer = response.getWriter();
  
          writer.println("saveDir = " + saveDir);
          writer.println("fileTypes = " + fileTypes);
      }
  }
```

Здесь мы объявляем сервлет ImageUploadServlet, который сопоставляем с шаблоном URL `/imageUpload` 
и указываем два параметра инициализации `saveDir` и `allowTypes`. Метод doGet() извлекает значения 
этих параметров и распечатывает (возвращает) их клиенту.

---
#### Объявляем сервлет с асинхронным режимом работы и порядком загрузки при запуске:

```java
  import javax.servlet.ServletConfig;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  
  @WebServlet(
          urlPatterns = "/myController",
          loadOnStartup = 1,
          asyncSupported = true
  )
  public class StartupServlet extends HttpServlet {
  
      public void init(ServletConfig config) {
          System.out.println("My servlet has been initialized");
      }
  
      // implement servlet doPost() and doGet()...
  }
```

Здесь мы объявляем сервлет StartupServlet с `loadOnStartup = 1` , и это означает,
что этот сервлет автоматически инициализируется контейнером сервлетов при запуске
сервера ( будет напечатано сообщение в методе init() ). Мы также указываем, что
сервлет поддерживает асинхронный режим.

---
**См. полные версии (ENG):**
- [Annotation Type WebServlet (from ORACLE)](https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/WebServlet.html)
- [Annotation Type WebServlet](https://jakarta.ee/specifications/platform/8/apidocs/javax/servlet/annotation/webservlet)
- [Annotation Type WebServlet (from APACHE)](https://tomcat.apache.org/tomcat-7.0-doc/servletapi/javax/servlet/annotation/WebServlet.html)

---
**Доп. материал:**
- [Java Servlet @WebServlet Annotation Example](https://www.codejava.net/java-ee/servlet/webservlet-annotation-examples)
- [A Guide to Java EE Web-Related Annotations](https://www.baeldung.com/javaee-web-annotations)
- [Servlet with Annotation](https://www.geeksforgeeks.org/java/servlet-with-annotation/)
- [Creating and Initializing a Servlet - The Java EE 6 Tutorial (from ORACLE)](https://docs.oracle.com/javaee/6/tutorial/doc/bnafu.html)
- [@WebServlet Annotation Example (Jakarta EE)](https://www.javaguides.net/2019/02/webservlet-annotation-example.html)
- [Complete Guide to @WebServlet in Java: Easy Servlet Mapping for Beginners](https://star-school.jp/en/servlet/410)
- [Servlets - Annotations (from TutorialsPoint.com)](https://www.tutorialspoint.com/servlets/servlets-annotations.htm)
- [Creating and Initializing a Servlet](https://javaee.github.io/tutorial/servlets004.html)

---
- [Java Platform, Enterprise Edition (Java EE) 8 The Java EE Tutorial](https://javaee.github.io/tutorial/toc.html)
- [Servlet Annotations in Java (@WebServlet) – Beginner’s Guide](https://w3htmlschool.com/servlet-annotations-in-java-webservlet-beginners-guide/)
- [How to Register a Servlet in Java](https://www.baeldung.com/register-servlet)
- [Servlet Annotation Example (from JavaTutorial.net)](https://javatutorial.net/servlet-annotation-example/)
