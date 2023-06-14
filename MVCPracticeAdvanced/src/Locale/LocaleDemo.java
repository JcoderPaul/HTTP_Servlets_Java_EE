package Locale;
/*
Класс: https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html
Документация: https://www.oracle.com/java/technologies/javase/jdk8-jre8-suported-locales.html
*/
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleDemo {
    public static void main(String[] args) {
        /* Пример создания локали руками, в объект передаем язык и страну */
        Locale myLocale = new Locale("ru","RU");
        System.out.println(myLocale);

        /* Предустановленные локали или константы */
        System.out.println(Locale.US);
        System.out.println(Locale.getDefault());

        /* Используем файл ресурсов translations.properties */
        ResourceBundle nowTranslations = ResourceBundle.getBundle("translations", myLocale);
        System.out.println(nowTranslations.getString("page.login.password"));

    }
}
