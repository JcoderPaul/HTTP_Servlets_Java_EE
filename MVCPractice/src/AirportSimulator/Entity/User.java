package AirportSimulator.Entity;

import AirportSimulator.Entity.EntityEnum.Gender;
import AirportSimulator.Entity.EntityEnum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/* Сокращаем написание кода используя Lombok см. DOC/Fast_Lombok.txt */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role; // Используем ENUM
    private Gender gender; // Используем ENUM
}
