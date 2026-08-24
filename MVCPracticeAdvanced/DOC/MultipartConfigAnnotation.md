- **См. исходник (ENG):** [Annotation Type MultipartConfig](https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/MultipartConfig.html)

---
### Аннотация @MultipartConfig

**Библиотека:** [javax.servlet.annotation](https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/package-summary.html)

```
@Target(value=TYPE)
@Retention(value=RUNTIME)
public @interface MultipartConfig
```

Аннотация @MultipartConfig поддерживает следующие необязательные атрибуты:

- ***location***: абсолютный путь к каталогу в файловой системе. Атрибут местоположения не поддерживает путь относительно контекста приложения. 
Это расположение используется для временного хранения файлов во время обработки частей или когда размер файла превышает указанный параметр 
fileSizeThreshold. **Расположение по умолчанию "".**

- ***fileSizeThreshold***: размер файла в байтах, после которого файл будет временно сохранен на диске. **Размер по умолчанию - 0 байт.**

- ***MaxFileSize***: Максимально допустимый размер загружаемых файлов в байтах. Если размер любого загруженного файла больше этого размера, 
веб-контейнер выдаст исключение ( IllegalStateException ). **Размер по умолчанию не ограничен.**

- ***maxRequestSize***: Максимальный размер, разрешенный для запроса multipart/form-data , в байтах. Веб-контейнер выдаст исключение, если 
общий размер всех загруженных файлов превысит этот порог. **Размер по умолчанию не ограничен.**

**Пример, аннотации @MultipartConfig:**

```java
  @MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
```

Вместо использования аннотации @MultipartConfig для жесткого кодирования этих атрибутов в сервлете загрузки файлов можно добавить следующий 
элемент в качестве дочернего элемента конфигурации сервлета в файле web.xml.

```xml
  <multipart-config>
      <location>/tmp</location>
      <max-file-size>20848820</max-file-size>
      <max-request-size>418018841</max-request-size>
      <file-size-threshold>1048576</file-size-threshold>
  </multipart-config>
```

---
**См. доп.:**
- [The Java EE 6 Tutorial - The @MultipartConfig Annotation (from ORACLE Java EE 6 Tutorial)](https://docs.oracle.com/javaee/6/tutorial/doc/gmhal.html)
- [Java Servlet @MultipartConfig Annotation Example](https://www.codejava.net/java-ee/servlet/multipartconfig-annotation-examples)
- [Annotation Type MultipartConfig (from tomcat.apache.org)](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/javax/servlet/annotation/MultipartConfig.html)
- [Annotation Type MultipartConfig (from jakarta.ee)](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/annotation/multipartconfig)
- [Getting Familiar with Spring’s @MultipartConfig for Uploading Files](https://medium.com/@AlexanderObregon/getting-familiar-with-springs-multipartconfig-for-uploading-files-97706a588e47)
- [@MultipartConfig Annotation Example (Jakarta EE)](https://www.javaguides.net/2019/02/multipartconfig-annotation-example.html)
- [Загрузка файла на сервер (java-online.ru)](https://java-online.ru/jsf-fileupload.xhtml)
- [How to activate @MultipartConfig annotation on a JSP file?](https://stackoverflow.com/questions/37965890/how-to-activate-multipartconfig-annotation-on-a-jsp-file)
- [Uploading Files with Java Servlet Technology (from javaee.github.io)](https://javaee.github.io/tutorial/servlets011.html)
- [Создание приложения для распознавания текста с изображений и аудиофайлов](https://habr.com/ru/articles/758882/)
