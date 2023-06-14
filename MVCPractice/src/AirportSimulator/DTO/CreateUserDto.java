package AirportSimulator.DTO;

import lombok.Builder;
import lombok.Value;

/*
Все DTO объекты должны быть имутабельны,
поэтому применяем Lombok аннотацию @Value
*/
@Value
@Builder
public class CreateUserDto {
    /*
    К сожалению все параметры из нашего запроса приходят
    в формате ключ-значение при этом оба в формате String,
    т.е. нам в дальнейшем придется провести преобразования,
    например даты в понятный для базы данных вид. Данные
    неудобства в профессиональных фреймворках реализованы
    за нас.
    */
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
