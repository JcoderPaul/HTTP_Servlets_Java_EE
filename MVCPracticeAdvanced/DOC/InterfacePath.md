- **[См. полную версию в исходнике (ENG)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html)**

---
### Interface Path (JAVA 8 - 11 есть изменения)

**Модуль:**  [java.base](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/module-summary.html)

**Пакет:**  [java.nio.file](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/package-summary.html)

**Наследуемые интерфейсы:** 
- [Comparable<Path>](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html),
- [Iterable<Path>](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Iterable.html),
- [Watchable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Watchable.html)

```
public interface Path extends Comparable<Path>,
                              Iterable<Path>,
                              Watchable
```

Объект, который может использоваться для поиска файла в файловой системе. Обычно это путь к файлу, зависящий от системы.

Путь представляет собой иерархический путь, состоящий из последовательности элементов имени каталога и файла, разделенных 
специальным разделителем или разделителем. Также может присутствовать корневой компонент, определяющий иерархию файловой 
системы. Элемент имени, наиболее удаленный от корня иерархии каталогов, является именем файла или каталога. 

Другие элементы имени — это имена каталогов. Путь может представлять корень, корень и последовательность имен или просто 
один или несколько элементов имени. Путь считается пустым путем, если он состоит только из одного пустого элемента имени. 
Доступ к файлу с использованием пустого пути эквивалентен доступу к каталогу файловой системы по умолчанию. Путь определяет 
методы: 
- [getFileName](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#getFileName()), 
- [getParent](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#getParent()), 
- [getRoot](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#getRoot())
- [subpath](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#subpath(int,int)),
для доступа к компонентам пути или подпоследовательности его элементов имени.

В дополнение к доступу к компонентам пути `Path` также определяет методы разрешения - [resolve](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#resolve(java.nio.file.Path))
и [resolveSibling](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#resolveSibling(java.nio.file.Path)) для объединения путей. Метод [relativize](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#relativize(java.nio.file.Path)), который можно использовать для построения относительного пути между двумя путями. Пути можно
сравнивать и тестировать друг с другом с помощью методов [startWith](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#startsWith(java.nio.file.Path)) и [endWith](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#endsWith(java.nio.file.Path)).

Этот интерфейс расширяет интерфейс [Watchable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Watchable.html), так что каталог, расположенный по
пути, может быть зарегистрирован с помощью [WatchService](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/WatchService.html), а записи в каталоге отслеживаются.

**ПРЕДУПРЕЖДЕНИЕ. Этот интерфейс предназначен только для тех, кто разрабатывает собственные реализации файловой системы. В будущих версиях к этому интерфейсу могут быть добавлены методы.**

---
#### Доступ к файлам

Пути могут использоваться с классом Files для работы с файлами, каталогами и другими типами файлов. Например, предположим, что мы хотим, чтобы [BufferedReader](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedReader.html) считывал текст из файла «access.log». Файл находится в каталоге «logs» 
относительно текущего рабочего каталога и имеет кодировку UTF-8.

```java
     Path path = FileSystems.getDefault().getPath("logs", "access.log");
     BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
```

---
#### Совместимость

Пути, связанные [с поставщиком по умолчанию](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/spi/FileSystemProvider.html), обычно совместимы с классом [java.io.File](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html). Пути, созданные другими поставщиками, вряд ли будут совместимы с абстрактными именами путей, представленными 
[java.io.File](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html). Метод [toPath](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html#toPath())
можно использовать для получения пути из абстрактного имени пути, представленного объектом [java.io.File](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html). Полученный 
путь можно использовать для работы с тем же файлом, что и объект [java.io.File](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html).

Кроме того, метод [toFile](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#toFile()) полезен для создания файла из строкового представления пути.

---
#### Параллелизм

Реализации этого интерфейса неизменяемы и безопасны для использования несколькими параллельными потоками.

---
### МЕТОДЫ JAVA 11 (некоторых нет в JAVA 8)

- `int compareTo(Path other)` - Лексикографически сравнивает два абстрактных пути.
- `default boolean	endsWith(String other)` - Проверяет, заканчивается ли этот путь путем, созданным путем преобразования заданной строки пути точно так, как указано в методе endWith(Path).
- `boolean	endsWith(Path other)` - Проверяет, заканчивается ли этот путь заданным путем.
- `boolean	equals(Object other)` - Проверяет этот путь на равенство с заданным объектом.
- `Path getFileName()` - Возвращает имя файла или каталога, обозначенного этим путем, как объект Path.
- `FileSystem getFileSystem()` - Возвращает файловую систему, создавшую этот объект.
- `Path getName(int index)` - Возвращает элемент имени этого пути в виде объекта Path.
- `int getNameCount()` - Возвращает количество элементов имени в пути.
- `Path getParent()` - Возвращает родительский путь или null, если у этого пути нет родителя.
- `Path getRoot()` - Возвращает корневой компонент этого пути в виде объекта Path или null, если этот путь не имеет корневого компонента.
- `int hashCode()` - Вычисляет хэш-код для этого пути.
- `boolean	isAbsolute()` - Сообщает, является ли этот путь абсолютным.
- `default Iterator<Path> iterator()` - Возвращает итератор по элементам имени этого пути.
- `Path normalize()` - Возвращает путь, который представляет собой этот путь с удаленными избыточными элементами имени.
                   
---
- `static Path	of(String first, String... more)` - Возвращает Path путем преобразования строки пути или последовательности строк, которые при соединении образуют строку пути.

Возвращает Path путем преобразования строки пути или последовательности строк, которые при соединении
образуют строку пути. Если more не указаны какие-либо элементы, значением параметра first является
строка пути для преобразования. Если more указывает один или несколько элементов, то каждая непустая
строка, включая first, считается последовательностью элементов имени и объединяется для формирования
строки пути.

Подробная информация о том, как соединяются строки, зависит от поставщика, но обычно они будут
соединяться с использованием name-separator в качестве разделителя. Например, если используется
разделитель имен "/" getPath("/foo","bar","gus"), то строка пути "/foo/bar/gus" преобразуется в
файл Path. Представляющий Path пустой путь возвращается, если first это пустая строка и more не
содержит непустых строк.

Получается Path путем вызова getPath метода default FileSystem

**!!! Обратите внимание, что, хотя этот метод очень удобен, его использование подразумевает предполагаемую
ссылку на значение по умолчанию FileSystem и ограничивает полезность вызывающего кода !!!** 

**!!! Следовательно, его не следует использовать в библиотечном коде, предназначенном для гибкого повторного использования !!!** 

Более гибкой альтернативой является использование существующего Path экземпляра в качестве привязки, например:

```java
     Path dir = ...
     Path path = dir.resolve("file");
```

**Параметры:**
- `first` - строка пути или начальная часть строки пути;
- `more` - дополнительные строки, которые нужно объединить, чтобы сформировать строку пути;

**Возвращает:** Path

**Исключения:** InvalidPathException - если строка пути не может быть преобразована в Path

**Доступен:** с JAVA 11

---
`static Path	of(URI uri)` - Возвращает путь путем преобразования URI.

Возвращает Path путем преобразования URI.

Этот метод выполняет итерацию по installed поставщикам, чтобы найти поставщика, который
идентифицируется URI scheme данного URI. Схемы URI сравниваются без учета регистра. Если
провайдер найден, то getPath вызывается его метод для преобразования URI.

В случае поставщика по умолчанию, определяемого схемой URI «файл», данный URI имеет непустой
компонент пути и неопределенные компоненты запроса и фрагмента. Наличие компонента полномочий
зависит от платформы. Возвращенный Path связан с default файловой системой.

Поставщик по умолчанию предоставляет аналогичную гарантию приема-передачи для File класса.
Для заданного Path p гарантируется, что:

```java
  Path.of(п п.toUri()).equals( .toAbsolutePath())
```

до тех пор, пока исходный Path, новый URI и новый Path создаются (возможно, разными вызовами)
на одной и той же виртуальной машине Java. Предоставляют ли другие поставщики какие-либо гарантии,
зависит от конкретного поставщика и, следовательно, не указано.

**Параметры:** uri- URI для преобразования

**Возвращает:** Path

**Исключения:**
- `IllegalArgumentException` - если предусловия на uri параметр не выполняются. Формат URI зависит от поставщика.
- `FileSystemNotFoundException` - Файловая система, идентифицируемая URI, не существует и не может быть создана автоматически, или поставщик, идентифицируемый компонентом схемы URI, не установлен.
- `SecurityException` - если установлен менеджер безопасности и он запрещает неопределенное разрешение на доступ к файловой системе

**Доступен:** с JAVA 11

---
- `default WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events)` - Регистрирует файл, расположенный по этому пути, в службе наблюдения.
- `WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers)` - Регистрирует файл, расположенный по этому пути, в службе наблюдения.
- `Path relativize(Path other)` - Создает относительный путь между этим путем и заданным путем.
- `default Path resolve(String other)` - Преобразует заданную строку пути в путь и разрешает ее по этому пути точно так, как указано в методе разрешения.
- `Path resolve(Path other)` - Разрешить данный путь против этого пути.
- `default Path resolveSibling(String other)` - Преобразует заданную строку пути в путь и сопоставляет его с родительским путем этого пути точно так, как указано в методе resolveSibling.
- `default Path resolveSibling(Path other)` - Разрешает данный путь относительно родительского пути этого пути.
- `default boolean	startsWith(String other)` - Проверяет, начинается ли этот путь с пути, созданного путем преобразования заданной строки пути точно так, как указано в методе opensWith(Path).
- `boolean	startsWith(Path other)` - Проверяет, начинается ли этот путь с заданного пути.
- `Path subpath(int beginIndex, int endIndex)` - Возвращает относительный путь, являющийся подпоследовательностью элементов имени этого пути.
- `Path toAbsolutePath()` - Возвращает объект Path, представляющий абсолютный путь этого пути.
- `default File toFile()` - Возвращает объект File, представляющий этот путь.
- `Path toRealPath(LinkOption... options)` - Возвращает реальный путь к существующему файлу.
- `String toString()` - Возвращает строковое представление этого пути.
- `URI toUri()` - Возвращает URI для представления этого пути.

---
**Методы, объявленные в интерфейсе [java.lang.Iterable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Iterable.html):** forEach, spliterator

---
- **[См. полную версию (ENG): Interface Path](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html)**

---
- [Java NIO2 Path API](https://www.baeldung.com/java-nio-2-path)
- [Uses of Interface java.nio.file.Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/class-use/Path.html)
- [Accessing Resources using Paths](https://dev.java/learn/java-io/file-system/file-path/)
- [Java Files, Path](https://codegym.cc/groups/posts/219-files-path)
- [Path getName(int) method in Java with Examples](https://www.geeksforgeeks.org/java/path-getnameint-method-in-java-with-examples/)
- [File and Directory Names in Java: File, Path, Paths](https://www.happycoders.eu/java/file-and-directory-names-file-path-paths/)
- [Understanding the java.nio.file.Path Class in Java](https://medium.com/javarevisited/understanding-the-java-nio-file-path-class-in-java-ff1b149b2d65)
- [Java NIO Path (with Examples)](https://howtodoinjava.com/java/nio/how-to-define-path-in-java-nio/)
- [Working with Paths](https://dev.java/learn/java-io/file-system/path/)
- [How to use Java NIO API](https://ducmanhphan.github.io/2020-05-20-How-to-use-Java-NIO-API/)
- [Java NIO - Path](https://www.tutorialspoint.com/java_nio/java_nio_path.htm)
