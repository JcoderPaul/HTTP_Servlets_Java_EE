package AirportSimulator.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }
    private static void loadProperties() {
         /*
        В блоке try -with-resources получаем (открываем)
        входящий поток данных из файла свойств.

        1. Метод getClassLoader() класса java.lang.Class
        используется для получения classLoader текущего
        объекта. Этот объект может быть классом, массивом,
        интерфейсом и т. д. Метод возвращает classLoader
        этого объекта.

        classLoader - это объект, отвечающий за загрузку
        классов. Класс ClassLoader является абстрактным
        классом. Учитывая двоичное имя класса, загрузчик
        класса должен попытаться найти или сгенерировать
        данные, составляющие определение класса. Типичная
        стратегия состоит в том, чтобы преобразовать имя в
        имя файла, а затем прочитать «файл класса» с этим
        именем из файловой системы.

        2. Метод getResourceAsStream() класса java.lang.Class
        используется для получения ресурса с указанным ресурсом
        текущего класса. Метод возвращает указанный ресурс
        данного класса в виде объекта InputStream.

        Метод принимает параметр resourceName, который является
        ресурсом для получения данных (например, пары KEY-VALUE,
        как у нас).
        */
        try(InputStream inputStream =
                    PropertiesUtil.class.
                            getClassLoader().
                            getResourceAsStream("application.properties")){
            /*
            Метод *.load() считывает список свойств
            (пары ключей и элементов) из входного
            потока байтов.
            */
            PROPERTIES.load(inputStream);
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
