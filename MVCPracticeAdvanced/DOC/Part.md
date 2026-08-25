- **См. оригинал (ENG):** [Interface Part](https://docs.oracle.com/javaee/7/api/javax/servlet/http/Part.html)

---
### Interface Part

**Библиотеки:** 
- [javax.servlet.http](https://docs.oracle.com/javaee/7/api/javax/servlet/http/package-summary.html)
- [jakarta.servlet.http](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/http/package-summary)

```
  public interface Part
```

Этот класс представляет элемент формы, полученный в рамках запроса POST `multipart/form-data`.

---
#### Методы

- `void delete()` - Удаляет базовое хранилище для элемента файла, включая удаление любого связанного временного файла на диске.
- `String getContentType()` - Получает тип содержимого этой части.
- `String getHeader(String name)` - Возвращает значение указанного заголовка mime в виде строки (String).
- `Collection<String> getHeaderNames()` - Получает имена заголовков этой части.
- `Collection<String> getHeaders(String name)` - Получает значения заголовка Part с заданным именем.
- `InputStream getInputStream()` - Получает содержимое этой части как InputStream.
- `String getName()` - Получает имя этой части.
- `long getSize()` - Возвращает размер этого fille.
- `String getSubmittedFileName()` - Получает имя файла, указанное клиентом..
- `void write(String fileName)` - Удобный метод записи загруженного элемента на диск.

---
**См. полный вариант(eng):** 
- [Interface Part](https://docs.oracle.com/javaee/7/api/javax/servlet/http/Part.html)
- [Interface Part](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/http/part)

---
**Доп. материал:**
- [Package jakarta.servlet.http](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/http/package-summary)
- [Package javax.servlet.http](https://docs.oracle.com/javaee/7/api/javax/servlet/http/package-summary.html)
- [Jakarta EE explained clearly - build modern enterprise applications with these APIs: Part 1](https://blog.doubleslash.de/en/software-technologien/coding-and-frameworks/jakarta-ee-standards/)
- [Jakarta EE explained clearly - build modern enterprise applications with these APIs: Part 2](https://blog.doubleslash.de/en/software-technologien/coding-and-frameworks/jakarta-ee-standards-2/)
- [Jakarta Messaging Concepts](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/messaging/jms-concepts/jms-concepts.html)
- [Jakarta EE Tutorial](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/index.html)
- [Making Readable Code With Dependency Injection and Jakarta CDI](https://medium.com/xgeeks/making-readable-code-with-dependency-injection-and-jakarta-cdi-ae82d0565a0)
- [An introduction to Jakarta Faces 4.0 by Examples](https://itnext.io/an-introduction-to-jakarta-faces-4-0-by-examples-d949a7093236)
- [Building a dynamic web application with integrated user interface and backend logic (from openliberty.io)](https://openliberty.io/guides/jakarta-faces.html)
- [Getting Started with Jakarta EE 9: How to Create a REST API with Jakarta EE 9](https://www.azul.com/blog/getting-started-with-jakarta-ee-9-how-to-create-a-rest-api-with-jakarta-ee-9/)
