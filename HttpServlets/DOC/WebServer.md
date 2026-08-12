### Web-server TomCat

Веб-сервер — сервер, который принимает HTTP-запросы от клиентов и выдает им HTTP ответы (как правило, вместе с HTML страницей,
изображением, файлом или другими данными).

Запрашиваемые ресурсы обознаются URL-адресами.

Одним из самых популярных веб-серверов с поддержкой Servlet API является - Apache Tomcat.

Большинство веб-серверов — сложные механизмы, которые состоят из различных компонентов, и каждый из них выполняет определенные
функции.

**Основные компоненты TOMCAT (неполный):**
- **Coyote** - HTTP Connector (коннектор), который принимают входящие запросы от клиентов. HTTP коннектор в Tomcat реализован 
при помощи компонента "Coyote".

Коннекторы принимают данные от клиента и передают их дальше в Tomcat Engine.

- **Catalina** - Servlet Container (сервлет контейнер) - Tomcat Engine обрабатывает полученный от клиента request.

---
**Доп. материалы:**
- [Apache Tomcat (on tomcat.apache.org)](https://tomcat.apache.org/)
- [Introduction to Apache Tomcat](https://www.geeksforgeeks.org/devops/introduction-to-apache-tomcat/)
- [About Apache Tomcat (on WIKI)](https://en.wikipedia.org/wiki/Apache_Tomcat)
- [Architecture of Apache Tomcat](https://www.geeksforgeeks.org/devops/architecture-of-apache-tomcat/)
- [Difference between Apache Tomcat Server and Apache Web Server](https://www.tutorialspoint.com/article/difference-between-apache-tomcat-server-and-apache-web-server)
- [How To Host Website On Tomcat Server ?](https://www.geeksforgeeks.org/devops/how-to-host-website-on-tomcat-server/)
- [Tomcat - A Minimalistic User's Guide](https://tomcat.apache.org/tomcat-3.2-doc/uguide/tomcat_ug.html)
- [Introduction to Apache Tomcat](https://www.baeldung.com/tomcat)
- [All You Need to Know About Apache Tomcat](https://medium.com/@seo-offpage-tb/all-you-need-to-know-about-apache-tomcat-ac87d35876d0)
