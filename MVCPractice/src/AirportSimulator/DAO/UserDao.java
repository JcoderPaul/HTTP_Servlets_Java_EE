package AirportSimulator.DAO;

import AirportSimulator.Entity.User;
import AirportSimulator.Util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao <Integer, User>{
    private static final UserDao INSTANCE = new UserDao();

    public UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
    /*
    При размещении нового пользователя в базу
    нам не нужен ID т.к. он генерируется базой
    и мы его получим при запросе.
    */
    private static final String SAVE_SQL_ENTER_USER =
                    "INSERT INTO " +
                    "users (name, birthday, email, password, role, gender) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?)";

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }
    /*
    Применяем аннотацию Lombok для обработки исключений, так же
    при генерации запроса мы хотим получить сгенерированный ID,
    поэтому применяется аргумент RETURN_GENERATED_KEYS
    */
    @Override
    @SneakyThrows
    public User save(User saveUserEntity) {
        /* Получаем соединение с базой и формируем запрос к ней */
        try(Connection connection =
                    ConnectionManager.getBaseConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SAVE_SQL_ENTER_USER, RETURN_GENERATED_KEYS)){

            preparedStatement.setObject(1, saveUserEntity.getName());
            preparedStatement.setObject(2, saveUserEntity.getBirthday());
            preparedStatement.setObject(3, saveUserEntity.getEmail());
            preparedStatement.setObject(4, saveUserEntity.getPassword());
            preparedStatement.setObject(5, saveUserEntity.getRole().name());
            preparedStatement.setObject(6, saveUserEntity.getGender().name());
            /* Передаем запрос в базу */
            preparedStatement.executeUpdate();
            /* Получаем сгенерированный базой данных ID */
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            /* Помещаем в наш Entity объект USER полученный ID */
            saveUserEntity.setId(generatedKeys.getObject("id", Integer.class));

            return saveUserEntity;
        }
    }
}
