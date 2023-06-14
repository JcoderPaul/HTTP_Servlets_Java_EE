package AirportSimulatorTwo.DTO;
/* Применим Lombok чтобы не писать рутинный код */
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Long id;
    String mail;
}
