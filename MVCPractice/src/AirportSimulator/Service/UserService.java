package AirportSimulator.Service;

import AirportSimulator.DAO.UserDao;
import AirportSimulator.DTO.CreateUserDto;
import AirportSimulator.Entity.User;
import AirportSimulator.Exception.ValidationException;
import AirportSimulator.Mapper.CreateUserMapper;
import AirportSimulator.Validator.CreateUserValidator;
import AirportSimulator.Validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/* Конструктор без аргументов с параметрами доступа PRIVATE */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    /*
    Три объекта необходимых для реализации объекта текущего класса:
    - Валидация;
    - Преобразование DTO в DAO;
    - Создание DAO объекта.
    */
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

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
    public Integer create(CreateUserDto userDto){
        /* Получаем результат валидации */
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        /* Если что-то пошло не так, генерируем наше исключение */
        if (!validationResult.isValid()) {
            throw  new ValidationException(validationResult.getErrors());
        }
        /*
        Если валидация прошла успешно,
        все поля заполнены, ошибок нет,
        из DTO создаем DAO
        */
        User userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);

        return userEntity.getId();
    }
}
