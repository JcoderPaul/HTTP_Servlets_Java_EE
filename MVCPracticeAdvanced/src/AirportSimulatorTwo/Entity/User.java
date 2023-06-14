package AirportSimulatorTwo.Entity;

import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Entity.EntityEnum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/* Сокращаем написание кода используя Lombok */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String image; // Храним путь к нашей картинке, путь пропишем в файле свойств
    private String password;
    private Role role; // Используем ENUM
    private Gender gender; // Используем ENUM
}
