package AirportSimulator.Exception;

import AirportSimulator.Validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> app_errors;

    public ValidationException(List<Error> app_errors){
        this.app_errors = app_errors;
    }
}
