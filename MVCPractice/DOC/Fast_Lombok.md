[Project Lombok](https://projectlombok.org/)

---
### Аннотации в Lombok

Наиболее полезные аннотации в библиотеке:

- **@Getter** и **@Setter** — предоставляют геттеры и сеттеры для поля. Могут использоваться как на уровне поля, так и на уровне класса.
- **@NoArgsConstructor** — используется для создания конструктора без аргументов.
- **@AllArgsConstructor** — генерирует параметризованный конструктор, который принимает один параметр для каждого поля и инициализирует их с его помощью. Аннотация необходима, если нужно создать объект класса, передав начальные значения полей в конструктор.
- **@ToString** — переопределяет метод toString() и создает для него реализацию по умолчанию. Она выводит имя класса и поля по порядку, разделяя их запятыми. Можно пропустить отдельные поля, если аннотировать их с помощью @ToString.Exclude.
- **@EqualsAndHashCode** — применяется для переопределения методов equals() и hashCode().
- **@Data** — объединяет аннотации *@ToString, @Getter, @Setter, @EqualsAndHashCode* и *@RequiredArgsConstructor* в одну. Предоставляет весь код, который обычно используется в классах моделей, например, геттеры для всех полей, сеттеры для всех нефинальных полей, реализацию по умолчанию для toString(), equals() и hashCode(), а также конструктор, который инициализирует все поля класса.
- **@NonNull** - обработка переменных, которые не должны получать null.
- **@Value** - создание неизменяемых классов, аналог Data, но для неизменяемых классов.

---
- **@Builder** - реализация паттерна bulder, @Singular – используется для объектов в единственном экземпляре (добавления элемента в коллекции и т.п.).

**ПРИМЕР:**

```java
  @Builder
  public class Example {
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;
  }
```

---
- **@SneakyThrows** - обертка проверяемых исключений.

**ПРИМЕР С АННОТАЦИЕЙ:**

```java
  @SneakyThrows(UnsupportedEncodingException.class)
  public String utf8ToString(byte[] bytes) {
    return new String(bytes, «UTF-8»);
  }
```

**ПРИМЕР БЕЗ АННОТАЦИИ:**

```java
public String utf8ToString(byte[] bytes) {
    try {
      return new String(bytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw Lombok.sneakyThrow(e);
    }
 }
```

---
- **@Synchronized** - аннотирование synchronized блоков.

**ПРИМЕР С АННОТАЦИЕЙ:**

```java
  private final Object readLock = new Object();
  
  @Synchronized
  public static void hello() {
      мой код ...;
  }
  
  @Synchronized
  public int answerToLife() {
     мой код ...;
  }
  
  @Synchronized("readLock")
  public void foo() {
     мой код ...;
  }
```

**ПРИМЕР БЕЗ АННОТАЦИИ:**

```java
  private static final Object $LOCK = new Object[0];
  private final Object $lock = new Object[0];
  private final Object readLock = new Object();
  
  public static void hello() {
    synchronized($LOCK) {
      ...;
     }
  }
  
  public int answerToLife() {
    synchronized($lock) {
      ...;
     }
  }
  
  public void foo() {
     synchronized(readLock) {
        ...;
     }
  }
```

---
- **@Cleanup** - простое определение ресурсов, так чтобы они автоматически закрывались после окончания работы кода. Альтернатива try-with-resources.

**ПРИМЕР С АННОТАЦИЕЙ:**

```java
  @Cleanup InputStream in = new FileInputStream(args[0]);
  @Cleanup OutputStream out = new FileOutputStream(args[1]);
  ...;
```

**ПРИМЕР БЕЗ АННОТАЦИИ:**

```java
InputStream in = new FileInputStream(args[0]);
      try {
OutputStream out = new FileOutputStream(args[1]);
      try {
        ...;
      } finally {
        if (out != null) {
          out.close();
        }
      }
      } finally {
        if (in != null) {
          in.close();
        }
      }
```

**Доп. материалы:**
- [Project Lombok](https://projectlombok.org/)
- [Be Careful With Lombok](https://levelup.gitconnected.com/be-careful-with-lombok-2e2edfc01110)
- [Introduction to Project Lombok](https://www.baeldung.com/intro-to-project-lombok)
- [Simplify Your Code with Lombok](https://codingnomads.com/intro-to-java-lombok-maven-gradle)
- [Introduction to Project Lombok in Java and How to Get Started?]()
- [https://www.geeksforgeeks.org/java/introduction-to-project-lombok-in-java-and-how-to-get-started/]()
- [Lombok Essentials: Enhancing Java Coding Efficiency and Maintainability](https://medium.com/@alxkm/lombok-essentials-enhancing-java-coding-efficiency-and-maintainability-1e8904dbefe1)
- [Lombok Using @With Annotations](https://www.baeldung.com/lombok-with-annotations)
- [A Complete Guide to Lombok](https://auth0.com/blog/a-complete-guide-to-lombok/)
- [10 Lombok Annotations Every Java Developer Should Know](https://dev.to/gianfcop98/10-lombok-annotations-every-java-developer-should-know-pcd)
- [Spring Boot - Using Lombok to Reduce Boilerplate Code](https://www.geeksforgeeks.org/advance-java/using-lombok-to-reduce-boilerplate-code-in-spring-boot/)
- [Understanding Lombok and Its Common Features](https://dev.to/rajibdk/understanding-lombok-and-its-common-features-18d3)
