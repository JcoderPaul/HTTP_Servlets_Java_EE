package AirportSimulatorTwo.Validator;

public interface Validator <T>{
    ValidationResult isValid(T object);
}
