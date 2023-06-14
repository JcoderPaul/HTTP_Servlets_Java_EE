package AirportSimulatorTwo.Mapper;

import AirportSimulatorTwo.DTO.ReadUserDto;
import AirportSimulatorTwo.Entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadUserMapper implements Mapper<User, ReadUserDto> {

    private static final ReadUserMapper INSTANCE = new ReadUserMapper();

    public static ReadUserMapper getInstance() {
        return INSTANCE;
    }
    @Override
    public ReadUserDto mapFrom(User object) {
        return ReadUserDto.builder().
                id(object.getId()).
                name(object.getName()).
                birthday(object.getBirthday()).
                email(object.getEmail()).
                image(object.getImage()).
                role(object.getRole()).
                gender(object.getGender()).
                build();
    }
}
