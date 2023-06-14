package AirportSimulator.Mapper;

import AirportSimulator.DTO.CreateUserDto;
import AirportSimulator.Entity.EntityEnum.Gender;
import AirportSimulator.Entity.EntityEnum.Role;
import AirportSimulator.Entity.User;
import AirportSimulator.Util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/* Конструктор без аргументов с параметрами доступа PRIVATE */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

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
                password(object.getPassword()).
                gender(Gender.valueOf(object.getGender())).
                role(Role.valueOf(object.getRole())).
                build();
    }
}
