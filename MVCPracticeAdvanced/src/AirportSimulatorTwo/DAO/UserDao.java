package AirportSimulatorTwo.DAO;

import AirportSimulatorTwo.Entity.EntityEnum.Gender;
import AirportSimulatorTwo.Entity.EntityEnum.Role;
import AirportSimulatorTwo.Entity.User;
import AirportSimulatorTwo.Util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
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
                    "users (name, birthday, email, password, role, gender, image) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";
    /* Делаем выборку из базы по email и password */
    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
                    "SELECT * FROM users WHERE email = ? AND password = ?";
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

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        /* Создаем связь и формируем заготовленный параметризированный запрос */
        try(Connection connectionToBase = ConnectionManager.getBaseConnection();
            PreparedStatement prepStatement = connectionToBase.
                    prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)){
            /* Задаем параметры запроса, соблюдаем порядок параметров */
            prepStatement.setString(1, email);
            prepStatement.setString(2, password);
            /* Отправляем запрос и получаем ответ */
            ResultSet resultSetOfQuery = prepStatement.executeQuery();
            User userFromBase = null;
            /*
            Есть вероятность того, что пользователя по заданным параметрам
            мы не найдем, отсюда и возвращаемый методом Optional объект,
            который при его наличии будет User.
            */
            if(resultSetOfQuery.next()){
                userFromBase = buildEntity(resultSetOfQuery);
            }
            return Optional.ofNullable(userFromBase);
        }
    }
    /*
    Данный метод необходим, чтобы вернуть извлеченные
    из базы данные - объект класса User.
    */
    private static User buildEntity(ResultSet resultSetOfQuery) throws SQLException {
        return User.builder().
                id(resultSetOfQuery.getObject("id", Integer.class)).
                name(resultSetOfQuery.getObject("name", String.class)).
                birthday(resultSetOfQuery.getObject("birthday", Date.class).toLocalDate()).
                email(resultSetOfQuery.getObject("email", String.class)).
                image(resultSetOfQuery.getObject("image", String.class)).
                password(resultSetOfQuery.getObject("password", String.class)).
                role(Role.find(resultSetOfQuery.getObject("role", String.class)).orElse(null)).
                gender(Gender.valueOf(resultSetOfQuery.getObject("gender", String.class))).
                build();
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
            /* Формируем запрос с возможностью извлечь ID внесенного пользователя */
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SAVE_SQL_ENTER_USER, RETURN_GENERATED_KEYS)){
            /* Передаем в запрос параметры переданные из формы */
            preparedStatement.setObject(1, saveUserEntity.getName());
            preparedStatement.setObject(2, saveUserEntity.getBirthday());
            preparedStatement.setObject(3, saveUserEntity.getEmail());
            preparedStatement.setObject(4, saveUserEntity.getPassword());
            preparedStatement.setObject(5, saveUserEntity.getRole().name());
            preparedStatement.setObject(6, saveUserEntity.getGender().name());
            preparedStatement.setObject(7, saveUserEntity.getImage());
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
