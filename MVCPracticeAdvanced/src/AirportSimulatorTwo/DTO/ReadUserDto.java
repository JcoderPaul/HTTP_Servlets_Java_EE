package AirportSimulatorTwo.DTO;

import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Entity.EntityEnum.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ReadUserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
