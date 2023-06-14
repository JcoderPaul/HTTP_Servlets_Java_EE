package AirportSimulatorTwo.Service;

import AirportSimulatorTwo.DAO.UserDao;
import AirportSimulatorTwo.DTO.CreateUserDto;
import AirportSimulatorTwo.DTO.ReadUserDto;
import AirportSimulatorTwo.Entity.User;
import AirportSimulatorTwo.Exception.ValidationException;
import AirportSimulatorTwo.Mapper.CreateUserMapper;
import AirportSimulatorTwo.Mapper.ReadUserMapper;
import AirportSimulatorTwo.Validator.CreateUserValidator;
import AirportSimulatorTwo.Validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

/* Конструктор без аргументов с параметрами доступа PRIVATE */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    /*
    Теперь четыре объекта необходимых для реализации объекта текущего класса:
    - Валидация 'createUserValidator';
    - Интеграция загружаемого файла в форму 'imageService';
    - Преобразование DTO в DAO 'createUserMapper';
    - Создание DAO объекта 'userDao'.
    */
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    /*
    Для корректной работы данного метода (да и нашего приложения в целом)

    нужно реализовать несколько шагов:
    1. пройти валидацию - т.е. проверить верны ли введенные данные;
    2. преобразование нашей DTO в сущность;
    3. сохраняем нашу сущность на уровне DAO;
    4. возвращаем id сущности или саму сущность (в зависимости от
       выбранного типа возвращаемых данных методом 'create').
    */
    @SneakyThrows
    public Integer create(CreateUserDto createUserDto){
        /* Получаем результат валидации */
        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        /* Если что-то пошло не так, генерируем наше исключение */
        if (!validationResult.isValid()) {
            throw  new ValidationException(validationResult.getErrors());
        }
        /*
        Если валидация прошла успешно,
        все поля заполнены, ошибок нет,
        из DTO создаем DAO
        */
        User userEntity = createUserMapper.mapFrom(createUserDto);
        /*
        Поскольку у нас простой проект, а не Spring, поступим просто,
        сначала загрузим картинку, и только за тем нашего пользователя,
        чтобы не было ошибок.
        */
        imageService.upLoadImg(userEntity.getImage(), createUserDto.getImage().getInputStream());
        userDao.save(userEntity);

        return userEntity.getId();
    }
    /*
    Как и в случае с базой данных или UserDao.java мы не
    знаем, есть ли в базе пользователь с введенными email
    и password, поэтому возвращаемый объект типа Optional

    Однако с уровня сервисов на уровень сервлетов поступает
    объект класса DTO и значит нам нужно преобразовать DAO в
    DTO, поэтому используется мапер, как параметр к методу *.map()

    Для преобразования DAO в DTO мы используем объект
    класса ReadUserMapper и его метод *.mapFrom().
    */
    public Optional<ReadUserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).
                map(object -> readUserMapper.mapFrom(object));
    }
}