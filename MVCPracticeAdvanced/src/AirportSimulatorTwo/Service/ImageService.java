package AirportSimulatorTwo.Service;

import AirportSimulatorTwo.Util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/*
Делаем простой синглтон. Добавляем аннотацию с
пустым конструктором, с приватным доступом.
*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    /* Базовый путь для хранения файлов (картинок) */
    private final String basePath = PropertiesUtil.get("image.base.url");

    public static ImageService getInstance() {
        return INSTANCE;
    }

    /* Аннотацией пробрасываем исключение */
    @SneakyThrows
    public void upLoadImg(String imagePath, InputStream imgContent){
        /*
        *** (JAVA 11) ***
        static Path	of(String first, String... more) - Возвращает Path путем преобразования строки
                                                       пути или последовательности строк, которые
                                                       при соединении образуют строку пути.

        Соединяем базовый путь и путь для хранения конкретного файла (картинки)
        */
        Path imageFullPath = Path.of(basePath, imagePath);
        try(imgContent){
            /* Создаем папку внутри исходной (основной) на случай если ее там нет */
            Files.createDirectories(imageFullPath.getParent());
            /*
            Читаем файл по указанному пути, читаем все наши байты, две стандартные опции:
            - создаем файл;
            - и перезаписываем если он есть.
            */
            Files.write(imageFullPath,
                        imgContent.readAllBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    /* Из-за того что может быть выброшено исключение применяем аннотацию */
    @SneakyThrows
    public Optional<InputStream> getImage(String imagePath){
        Path imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }
}
