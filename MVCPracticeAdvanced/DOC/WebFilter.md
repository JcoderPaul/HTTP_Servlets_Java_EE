- [См. оригинал (ENG)](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/annotation/webfilter)

### Annotation Type WebFilter

```java
  @Target(TYPE)
  @Retention(RUNTIME)
  @Documented
  public @interface WebFilter
```

**Библиотека:** [jakarta.servlet.annotation](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/annotation/package-summary)

Аннотация, используемая для объявления фильтра сервлета. Эта аннотация обрабатывается контейнером во время 
развертывания, и соответствующий фильтр применяется к указанным шаблонам URL, сервлетам и типам диспетчера.

**См. так же:** [Filter](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/filter)

---
#### Необязательные параметры:

- `boolean asyncSupported` - Указывает, поддерживает ли фильтр асинхронный режим работы.
- `String description` - Описание фильтра
- `DispatcherType[] dispatcherTypes` - Типы диспетчеров, к которым применяется фильтр
- `String displayName` - Отображаемое имя фильтра
- `String filterName` - Название фильтра
- `WebInitParam[] initParams` - Начальные параметры фильтра
- `String largeIcon` - Большая иконка фильтра
- `String[] servletNames` - Имена сервлетов, к которым применяется фильтр.
- `String smallIcon` - Маленькая иконка фильтра
- `String[] urlPatterns` - Шаблоны URL, к которым применяется фильтр
- `String[] value` - Шаблоны URL, к которым применяется фильтр. Значение по умолчанию — пустой массив.

---
- [См. Annotation Type WebFilter (ENG)](https://jakarta.ee/specifications/platform/9/apidocs/jakarta/servlet/annotation/webfilter)

---
**Доп. материал:**
- [Java Servlet @WebFilter Annotation Example](https://www.codejava.net/java-ee/servlet/webfilter-annotation-examples)
- [A Guide to Java EE Web-Related Annotations](https://www.baeldung.com/javaee-web-annotations)
- [@WebFilter Annotation Example (Jakarta EE)](https://www.javaguides.net/2019/02/webfilter-annotation-example.html)
- [Java Servlet Filter with Example](https://www.geeksforgeeks.org/java/java-servlet-filter-with-example/)
- [Filtering Requests and Responses - Java Platform, Enterprise Edition: The Java EE Tutorial (from ORACLE)](https://docs.oracle.com/javaee/7/tutorial/servlets006.htm)
- [ava EE Filter Design Pattern Example](https://examples.javacodegeeks.com/java-development/enterprise-java/java-ee-filter-design-pattern-example/)
- [Create Servlet Filter using @WebFilter](https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/web-filter-example.html)
- [Dynamically registering WebFilter with Java EE 6](https://blog.eisele.net/2011/06/dynamically-registering-webfilter-with.html)
- [How to Create Java Servlet Filter](https://www.codejava.net/java-ee/servlet/how-to-create-java-servlet-filter)
- [How to Use Filter in Servlet 3 with @WebFilter Annotation](https://www.concretepage.com/java-ee/jsp-servlet/how-to-use-filter-in-servlet-3-with-webfilter-annotation)
- [Servlet @WebFilter Annotation](https://www.cosmiclearn.com/servlet/web_filter_annotation.php)
- [How to modify HTTP response using Java Filter](https://www.codejava.net/java-ee/servlet/how-to-modify-http-response-using-java-filter)
