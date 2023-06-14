package AirportSimulator.Validator;

public interface Validator <T>{
    ValidationResult isValid(T object);
}
