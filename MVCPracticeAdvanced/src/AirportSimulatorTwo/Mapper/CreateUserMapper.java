package AirportSimulatorTwo.Mapper;

import AirportSimulatorTwo.DTO.CreateUserDto;
import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Entity.EntityEnum.Role;
import AirportSimulatorTwo.Entity.User;
import AirportSimulatorTwo.Util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/* Конструктор без аргументов с параметрами доступа PRIVATE */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_USER_FOLDER = "users/";

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
    /*
    Для преобразования полученной из формы регистрации даты (String)
    из строки в дату удобную для понимания базой данных LocalDate
    применяем самописный утиллитный класс LocalDateFormatter
    */
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder().
                name(object.getName()).
                birthday(LocalDateFormatter.format(object.getBirthday())).
                email(object.getEmail()).
                image(IMAGE_USER_FOLDER + object.getImage().getSubmittedFileName()).
                password(object.getPassword()).
                gender(Gender.valueOf(object.getGender())).
                role(Role.valueOf(object.getRole())).
                build();
    }
}
