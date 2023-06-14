package AirportSimulator.Mapper;

public interface Mapper <F, T> {
    T mapFrom(F object);
}
