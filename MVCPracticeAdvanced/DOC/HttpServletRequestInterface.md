- [См. исходник (ENG): HttpServletRequest](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html)

### Interface HttpServletRequest

**Все родительские интерфейсы:** [ServletRequest](https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequest.html)
**Все известные реализующие классы:** [HttpServletRequestWrapper](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequestWrapper.html)

```
  public interface HttpServletRequest extends ServletRequest
```

Расширяет [ServletRequest](https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequest.html) интерфейс для предоставления информации запроса для сервлетов HTTP.

Контейнер сервлета создает HttpServletRequest объект и передает его в качестве аргумента
служебным методам сервлета ( doGet, doPost, и т.д.).

---
#### ПАРАМЕТРЫ (ПОЛЯ)

- `String BASIC_AUTH` - Строковый идентификатор для обычной аутентификации.
- `String CLIENT_CERT_AUTH` - Строковый идентификатор для проверки подлинности сертификата клиента.
- `String DIGEST_AUTH` - Строковый идентификатор для дайджест-аутентификации.
- `String FORM_AUTH` - Строковый идентификатор для проверки подлинности с помощью формы.

---
#### МЕТОДЫ

- `boolean authenticate(HttpServletResponse response)` - Используйте механизм входа в контейнер, настроенный для ServletContext проверки подлинности пользователя, выполняющего этот запрос.
- `String getAuthType()` - Возвращает имя схемы аутентификации, используемой для защиты сервлета.
- `String getContextPath()` - Возвращает часть URI запроса, которая указывает контекст запроса.
- `Cookie[] getCookies()` - Возвращает массив, содержащий все Cookie объекты, отправленные клиентом с этим запросом.
- `long getDateHeader(String name)` - Возвращает значение указанного заголовка запроса как long значение, представляющее Date объект.
- `String getHeader(String name)` - Возвращает значение указанного заголовка запроса в виде файла String.
- `Enumeration<String>	getHeaderNames()` - Возвращает перечисление всех имен заголовков, содержащихся в этом запросе.
- `Enumeration<String>	getHeaders(String name)` - Возвращает все значения указанного заголовка запроса в виде Enumeration объектов String.
- `int getIntHeader(String name)` - Возвращает значение указанного заголовка запроса в виде файла int.
- `String getMethod()` - Возвращает имя метода HTTP, с помощью которого был сделан этот запрос, например, GET, POST или PUT.
- `Part getPart(String name)` - Получает Part с заданным именем.
- `Collection<Part> getParts()` - Получает все Part компоненты этого запроса при условии, что он имеет тип multipart/form-data.
- `String getPathInfo()` - Возвращает любую дополнительную информацию о пути, связанную с URL-адресом, отправленным клиентом при выполнении этого запроса.
- `String getPathTranslated()` - Возвращает любую дополнительную информацию о пути после имени сервлета, но перед строкой запроса, и преобразует ее в реальный путь.
- `String getQueryString()` - Возвращает строку запроса, содержащуюся в URL-адресе запроса после пути.
- `String getRemoteUser()` - Возвращает логин пользователя, делающего этот запрос, если пользователь прошел аутентификацию или null если пользователь не прошел аутентификацию.
- `String getRequestedSessionId()` - Возвращает идентификатор сеанса, указанный клиентом.
- `String getRequestURI()` - Возвращает часть URL-адреса этого запроса от имени протокола до строки запроса в первой строке HTTP-запроса.
- `StringBuffer getRequestURL()` - Восстанавливает URL-адрес, который клиент использовал для выполнения запроса.
- `String etServletPath()` - Возвращает часть URL этого запроса, которая вызывает сервлет.
- `HttpSession getSession()` - Возвращает текущий сеанс, связанный с этим запросом, или, если запрос не имеет сеанса, создает его.
- `HttpSession	getSession(boolean create)` - Возвращает текущий, HttpSession связанный с этим запросом, или, если текущий сеанс отсутствует и create имеет значение true, возвращает новый сеанс.
- `Principal getUserPrincipal()` - Возвращает Principal объект, содержащий имя текущего аутентифицированного пользователя.
- `boolean isRequestedSessionIdFromCookie()` - Проверяет, пришел ли запрошенный идентификатор сеанса в виде файла cookie.
- `boolean isRequestedSessionIdFromUrl()` - Устарело. Начиная с версии 2.1 Java Servlet API, используйте isRequestedSessionIdFromURL() вместо этого.
- `boolean isRequestedSessionIdFromURL()` - Проверяет, пришел ли запрошенный идентификатор сеанса как часть URL-адреса запроса.
- `boolean isRequestedSessionIdValid()` - Проверяет, действителен ли запрошенный идентификатор сеанса.
- `boolean isUserInRole(String role)` - Возвращает логическое значение, указывающее, включен ли аутентифицированный пользователь в указанную логическую «роль».
- `void login(String username,String password)` - Подтвердите предоставленное имя пользователя и пароль в области проверки пароля, используемой механизмом входа в веб-контейнер, настроенным для ServletContext.
- `void logout()` - Установить null как значение, возвращаемое при вызове getUserPrincipal, getRemoteUser, и getAuthType по запросу.

---
**Методы, унаследованные [от интерфейса ServletRequest](https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequest.html):** getAsyncContext, getAttribute, getAttributeNames, getCharacterEncoding, getContentLength, getContentType, getDispatcherType, getInputStream, getLocalAddr, getLocale, getLocales, getLocalName, getLocalPort, getParameter, getParameterMap, getParameterNames, getParameterValues, getProtocol, getReader, getRealPath, getRemoteAddr, getRemoteHost, getRemotePort, getRequestDispatcher, getScheme, getServerName, getServerPort, getServletContext, isAsyncStarted, isAsyncSupported, isSecure, removeAttribute, setAttribute, setCharacterEncoding, startAsync, startAsync

---
- [См. исходник (ENG): HttpServletRequest](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html)

---
**См. доп. материал:**
- [Servlet - Request Interface](https://www.geeksforgeeks.org/java/servlet-request-interface/)
- [Uses of Interface jakarta.servlet.http.HttpServletRequest](https://jakarta.ee/specifications/servlet/6.0/apidocs/jakarta.servlet/jakarta/servlet/http/class-use/httpservletrequest)
- [HttpServletRequest Interface with Example (Jakarta EE)](https://www.javaguides.net/2019/03/httpservletrequest-interface-with-example.html)
- [Reading HttpServletRequest Multiple Times in Spring](https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times)
- [Advanced Java — ServletRequest](https://medium.com/@SachinPandeyOnline/advanced-java-servletrequest-inte-d5ea77574f4d)
- [HttpServletRequest.java (code)](https://nightlies.apache.org/tomcat/tomcat-12.0.x/coverage/jakarta.servlet.http/HttpServletRequest.java.html)
- [HttpServletRequest.java (GitHub code)](https://github.com/jboss/jboss-servlet-api_spec/blob/master/src/main/java/javax/servlet/http/HttpServletRequest.java)
- [ServletRequest Interface with example](https://beginnersbook.com/2013/05/servlet-request-interface/)
- [Java Examples for javax.servlet.http.HttpServletRequest](https://www.javatips.net/api/javax.servlet.http.httpservletrequest)
- [Set a parameter in a Java HttpServletRequest](https://examples.javacodegeeks.com/set-a-parameter-in-a-java-httpservletrequest/)
