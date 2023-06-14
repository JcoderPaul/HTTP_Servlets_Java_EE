package AirportSimulatorTwo.DTO;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

/*
Все DTO объекты должны быть имутабельны,
поэтому применяем Lombok аннотацию @Value
*/
@Value
@Builder
public class CreateUserDto {

    String name;
    String birthday;
    String email;
    Part image;
    String password;
    String role;
    String gender;
}
