package AirportSimulatorTwo.Validator;
/* Класс описывающий ошибки при валидации */
import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    private String code;
    private String message;
}
