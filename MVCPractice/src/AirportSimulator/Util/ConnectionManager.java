package AirportSimulator.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASS_KEY = "db.pass";

    static {
        loadPostgreSqlDriver();
    }

    private static void loadPostgreSqlDriver() {
            /*
            Метод forName() класса java.lang.Class возвращает объект Class,
            связанный с классом или интерфейсом с заданным строковым именем.
            Вызов этого метода эквивалентен:

            Class.forName(className, true, currentLoader)

            , где currentLoader обозначает определяющий загрузчик класса текущего
            класса. Например, следующий фрагмент кода возвращает дескриптор класса
            среды выполнения для класса с именем java.lang.Thread:

            Class t = Class.forName("java.lang.Thread")

            Вызов forName("XYZ") приводит к инициализации класса с именем XYZ.

            Параметры: className — полное имя вызываемого класса.
            Возвращает: объект Class для класса с указанным именем.

            Исключения:
            - LinkageError - если связь не удалась
            - ExceptionInInitializerError - если инициализация, спровоцированная
                                            этим методом, не удалась.
            - ClassNotFoundException - если класс не может быть расположен

            В нашем случае метод Class.forName загружает класс драйвера,
            который мы будем использовать.
            */
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager(){
    }

    public static Connection getBaseConnection(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASS_KEY));
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
}
