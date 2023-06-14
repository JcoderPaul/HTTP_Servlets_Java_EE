package AirportSimulatorTwo.Validator;

import AirportSimulatorTwo.DTO.CreateUserDto;
import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto>{

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
    /* Проверяем на валидность - правильность введенную дату и пол */
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.addErrors(Error.of("invalid.birthday","Birthday is invalid"));
        }
        if (object.getGender() == null || Gender.valueOf(object.getGender()) == null) {
            validationResult.addErrors(Error.of("invalid.gender","Gender is invalid"));
        }

        return validationResult;
    }
}
