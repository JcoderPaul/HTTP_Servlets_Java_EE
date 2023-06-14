package AirportSimulator.Validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    @Getter
    private List<Error> errors = new ArrayList<>();

    public void addErrors(Error find_error) {
        this.errors.add(find_error);
    }

    public boolean isValid(){
        return errors.isEmpty();
    }
}
