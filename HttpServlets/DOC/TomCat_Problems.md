**1.** При запуске TomCat - `startup.bat` через консль могут возникнуть проблемы (у меня возникли), например, такие: 

```
"JRE_HOME variable is not defined correctly. The environment variable is needed to Run this program..."
```
 
Для решения пришлось настраивать переменные среды (win 7):

- *Переменные среды пользователя:*

```
CATALINA_HOME=c:\Apache Software\Tomcat 10.0\; (path where your Apache Tomcat is)
JAVA_HOME=C:\Program Files\Java\jdk1.8.0_25; (path where your JDK is)
JRE_Home=C:\Program Files\Java\jre1.8.0_25; (path where your JRE is)
```

- *Системные переменные:*

```
CLASSPATH=%JAVA_HOME%\bin;%JRE_HOME%\bin;%CATALINA_HOME%\lib
```

**2.** Так же, при попытке запуска - `shutdown.bat` через консоль, сервер отказывался выключаться пришлось коректировать 
файл server.xml, который сожно найти, например, в папке:

```
c:\Apache Software\Tomcat 10.0\conf\
```

Далее в этом файле необходимо строку содержащую:

```
 <Server port="-1" shutdown="SHUTDOWN">
```
заменить на:

```
<Server port="${port.shutdown}" shutdown="SHUTDOWN">
```

Для меня это сработало. 

---
**См. доп.:** 
- [Running The Apache Tomcat 8.0 Servlet/JSP Container](https://tomcat.apache.org/tomcat-8.0-doc/RUNNING.txt)
- [Getting Error:JRE_HOME variable is not defined correctly when trying to run startup.bat of Apache-Tomcat](https://stackoverflow.com/questions/28991391/getting-errorjre-home-variable-is-not-defined-correctly-when-trying-to-run-star)
- [The JRE_HOME environment variable is not defined correctly", While Launching the Bamboo Server](https://community.atlassian.com/forums/Bamboo-questions/The-JRE-HOME-environment-variable-is-not-defined-correctly-quot/qaq-p/2115213)
- [Maven Error “JAVA_HOME should point to a JDK not a JRE”](https://www.baeldung.com/maven-java-home-jdk-jre)
- [JAVA and Tomcat — missing or wrong JAVA_HOME , JRE_HOME](https://medium.com/@konradkozlowski/java-and-tomcat-missing-or-wrong-java-home-3c891905c438)
- [The JAVA_HOME environment variable is not defined correctly](https://forums.oracle.com/ords/apexds/post/the-java-home-environment-variable-is-not-defined-correctly-0679)
- [Fix JAVA_HOME errors | Invalid directory | Not set or defined | Points to JRE](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-to-fix-common-JAVA_HOME-errors-quickly)
