### DAO - Data Access Object

**В программном обеспечении data access object (DAO) — абстрактный интерфейс к какому-либо
типу базы данных или механизму хранения.** Определённые возможности предоставляются независимо
от того, какой механизм хранения используется и без необходимости специальным образом
соответствовать этому механизму хранения. 

Этот шаблон проектирования применим ко множеству языков программирования, большей части 
программного обеспечения, нуждающемуся в хранении информации, и к большей части баз данных. 
Но традиционно этот шаблон связывают с приложениями на платформе Java Enterprise Edition (Java EE), 
взаимодействующими с реляционными базами данных через интерфейс JDBC, потому что он появился 
в рекомендациях от фирмы Sun Microsystems.

**В общем случае, определение Data Access Object описывает его как прослойку между БД и системой.**
DAO абстрагирует сущности системы и делает их отображение на БД, определяет общие методы
использования соединения, его получение, закрытие и (или) возвращение в Connection Pool.

Вершиной иерархии DAO является абстрактный класс или интерфейс с описанием общих методов,
которые будут использоваться при взаимодействии с базой данных. Как правило, это методы поиска,
удаление по ключу, обновление и т.д.

```java
    public abstract class AbstractController <K, E> {
        public abstract List<E> getAll();
        public abstract E getEntityById(K id);
        public abstract E update(E entity);
        public abstract boolean delete(K id);
        public abstract boolean create(E entity);
    }
```

Набор методов не является завершённым, он зависит от конкретной системы. Фиктивный тип 'K' является
ключом сущности, редкая таблица, описывающая сущность, не имеет первичного ключа.

---
**Доп. материал:**
- [The DAO Pattern in Java](https://www.baeldung.com/java-dao-pattern)
- [Data Access Object(DAO) Design Pattern](https://www.geeksforgeeks.org/system-design/data-access-object-pattern/)
- [Data Access Object (from oracle.com)](https://www.oracle.com/java/technologies/data-access-object.html)
- [Core J2EE Patterns - Data Access Object (from oracle.com)](https://www.oracle.com/java/technologies/dataaccessobject.html)
- [Abstracting Data Access in Java With the DAO Pattern](https://foojay.io/today/abstracting-data-access-in-java-with-the-dao-pattern/)
- [Demo-DAO-JDBC code (from GitHub)](https://github.com/pablomathdev/Demo-DAO-JDBC)
- [Spring DAO Support](https://docs.spring.io/spring-framework/reference/data-access/dao.html)
- [What is Data access object (DAO) in Java](https://stackoverflow.com/questions/19154202/what-is-data-access-object-dao-in-java)
- [Data Access Object Pattern in Java: Streamlining Database Interaction](https://java-design-patterns.com/patterns/data-access-object/#programmatic-example-of-dao-pattern-in-java)
- [DAO pattern in Java](https://ducmanhphan.github.io/2019-02-15-DAO-pattern-in-java/)
- [Data Access Object Pattern](https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm)
