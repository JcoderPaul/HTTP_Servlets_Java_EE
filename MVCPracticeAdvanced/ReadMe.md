### Вспомогательные папки для всех разделов:

- [BASE](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/BASE) - SQL скрипты для создания и заполнения базы данных;
- [DOC](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/DOC) - описания классов и интерфейсов используемых в данном проекте;
- [lib](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/lib) - файлы сторонних библиотек: lombok, postgresql - коннектор и библиотека servlet-api;
- [resources](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/resources) - папка ресурсов (в данном примере содержит только файл свойств);

### Часть 1 - Загрузка бинарного файла через форму регистрации на 'удаленный' сервер

В данном случае необходимо внести изменения и добавления в предыдущий код из AirportSimulator:
- В нашем [Entity](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Entity) объекте [USER](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Entity/User.java) добавляем поле 'image' (см. Entity/User); 
- В базу данных, в таблицу USERS, добавить поле с адресом картинки (файла), т.е. мы не загружаем саму картинку (файл) в базу данных, а размещаем лишь ссылку на нее, сам же файл хранится в некой папке на сервере, куда мы его загрузили при помощи формы регистрации (см. [BASE/create_users_table.sql](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/BASE/create_users_table.sql));
- В нашем [UserDao](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/DAO/UserDao.java) вносим изменения согласно задуманного, т.е. добавляем поле и параметр '?' в SAVE_SQL_ENTER_USER, а так же в метод User save добавляем седьмой параметр (см. [DAO/UserDao](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/DAO/UserDao.java));
- Изменить форму обратной связи, добавить необходимые теги полей взаимодействия с пользователем type="file", а так же добавить enctype="multipart/form-data" в теге таблицы (см. [WEB-INF/jsp/jstl_img_reg_form.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/jstl_img_reg_form.jsp));
- Вносим изменения в сервлет который обрабатывает запрос от пользователя и переводит его на форму регистрации *.JSP. Для этого в методе doPost немного изменяем схему создания userDto - добавляем нашу загружаемую на сервер картинку, используем класс Part, а также дополнительную аннотацию к заголовку сервлета @MultipartConfig (см. [Servlet/UserRegForm/UserRegForm](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Servlet/UserRegForm/UserRegForm.java)).
- Определяем корневую папку на нашем сервере куда будут сохраняться наши картинки, это мы сделаем через файл свойств application.properties, добавив необходимую пару ключ = значение (см. [resources/application.properties](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/resources/application.properties)); 
- Создадим сервис, который позволит сохранять переданный через форму регистрации файл на 'наш сервер' он реализует метод public void upLoadImg(String imagePath, InputStream imgContent) (см. [Service/ImageService](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Service/ImageService.java));
- Вносим небольшие изменения в UserService, в его метод public Integer create(CreateUserDto createUserDto), а именно добавляем объект imageService и используем его метод загрузки картинки *.upLoadImg() (см. [Service/UserService](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Service/UserService.java));
- Вносим изменения в наш преобразователь сущностей [CreateUserMapper](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Mapper/CreateUserMapper.java), а именно добавляем поле IMAGE_USER_FOLDER, определяющее куда конкретно будет помещена картинка (файл) пользователя и конечно добавляем поле 'image' в метод public User mapFrom(CreateUserDto object) (см. [Mapper/CreateUserMapper](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Mapper/CreateUserMapper.java));

### Часть 2 - Отображение картинки на форме обратной связи

Предполагается, что на первом этапе мы загрузили на сервер в конкретную папку некий файл (картинку), теперь мы их хотим отобразить на странице формы, для этого: 

- Добавляем соответствующий тег в тело страницы, где отображается наша форма регистрации <img src="${pageContext.request.contextPath}/Image/users/birthday.jpg"
  alt="User image"> (см. WEB-INF/jsp/jstl_img_reg_form.jsp);
- Создаем сервлет позволяющий загрузить (отобразить) картинку на страницу. Однако в параметрах аннотации [@WebServlet, мы прописываем следующий URL паттерн - "/Image/*"](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/ImageServlet/ImageServlet.java), что вкратце означает, то нас интересуют все URL начинающиеся на '/Image', а все что после - обозначенные значком 'астериск' или "\*" нас не интересуют, т.к. сразу сработает данный сервлет. Т.е. например, при обращении к адресу http://localhost:8080/Image/ будет активирован данный сервлет (см. [ImageServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/ImageServlet/ImageServlet.java));
- В класс [ImageService](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Service/ImageService.java) добавляем метод позволяющий прогружать картинку (файл) public Optional<InputStream> getImage(String imagePath). Данный метод возвращает Optional объект т.к. неясно на этапе загрузки есть ли требуемый файл на сервере (см. [AirportSimulatorTwo/Service/ImageService](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Service/ImageService.java)).

Сервлет [ImageServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/ImageServlet/ImageServlet.java) возвращающий картинку в response, на данном этапе имеет два метода:
- protected void doGet(HttpServletRequest req, HttpServletResponse resp) - классический метод сервлетов, обрабатывающий запрос пользователей;
- private void writeImage(InputStream image, HttpServletResponse resp) - метод используемый в методе *.doGet() данного сервлета, который позволяет входящий поток преобразовать в исходящий (т.е. считать картинку с сервера и отобразить ее на странице пользователя, как вариант);

Стоит обратить внимание, что при обращении к странице http://localhost:8080/registration браузер делает два запроса, сначала на получение формы регистрации, а затем на получение картинки. Это легко заметить при пошаговой работе программы и отслеживании ответов сервера в браузерном режиме разработчика.

### Часть 3 - Фильтры в сервлетах

Обычно на один конкретный запрос отвечает один конкретный сервлет (или ни одного - 404). С фильтрами ситуация немного другая, это сервлет (или набор сервлетов см. рис. [DOC/ServletFilter.jpg](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/DOC/ServletFilter.jpg)) помеченный(ых) аннотацией @WebFilter который находится между клиентом и основным сервлетом (см. [DOC/WebFilter.txt](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/DOC/WebFilter.txt)). 

Веб-фильтры так же могут обрабатывать запрос клиента, еще до того как они попадут к сервлету с 'бизнес-логикой' и так же могут (возвращают) ответ пользователю. При этом их может быть несколько на один и тот же URL адрес, т.е. они могут идти каскадом и передавать запрос от одного фильтра к другому, затем сервлету и обратно передать ответ пользователю.

Обычно для настройки такого каскадного перекрытия фильтров друг-другом используется знак астериск - '*'. Такой каскад очень удобен, поскольку позволяет доработать логику основного сервлета, например реализовать сквозную кодировку всех сервлетов, или аутентификацию через веб-фильтры и т.п.

Нужно четко понимать, что как запрос движется по цепочке фильтров (filter chain) в сторону веб-сервлета, так и ответ пользователю движется в обратную сторону по той же цепочке и это так же можно использовать при работе с запрос/ответами.

- Создадим веб-фильтр [CharsetFilter](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Filter/Filters/CharsetFilter.java), который задает сквозную кодировку для всех страниц по запросу пользователя (в параметрах использован "/*");
- Используя разнообразные настройки параметров веб-фильтров, можно перенаправлять запросы на нужные страницы (см. [ErrorFilter](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Filter/Filters/ErrorFilter.java)). В данном случае при запросе к сервлету с URL '/ErrorImitation' фильтр в случае ошибки перенаправляет запрос на страницу [500.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/500.jsp). Чтобы перенаправление сработало должным образом необходимо в [web.xml](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/web.xml) прописать требуемые теги.
- Создадим веб-фильтр [UnSafeFilter](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Filter/Filters/UnSafeFilter.java), который при выполнении определенного условия пропустит запрос/ответ на целевой сервлет, в случае же его не выполнения перенаправит пользователя на страницу регистрации или сервлет с другим URL. Стоит обратить внимание, что сервлета с целевым URL '/unsafe' у нас нет.
- Создадим веб-фильтр [LoggingFilter](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Filter/Filters/LoggingFilter.java), который будет выводить на консоль все параметры запроса, данный фильтр будет срабатывать при любом запросе пользователя за счет параметра '/*'.

Названия фильтров не гарантирует нам порядок их вызова при обращении к определенному сервлету, порядок вызова в цепочке фильтров можно настроить руками через web.xml файл. 

### Часть 4 - Аутентификация, вход в WEB - приложение (LOGIN)

Процедура проверки подлинности. Например, мы хотим сравнить введенные пользователем данные с данными в базе или введенные им же в форме регистрации (которые должны попасть в базу данных). 

- Создадим страницу с формой аутентификации (см. [WEB-INF/jsp/login.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/login.jsp)). Форма будет содержать два поля для заполнения Email и Password.
- Создадим сервлет (см. [Authentication/LogInServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authentication/LogInServlet.java)), который будет получать запрос от пользователя и обрабатывать его:

  - Метод doGet перенаправит (*.forward(req, resp)) пользователя на форму аутентификации login.jsp;
  - После получения данных методом POST в работу вступает метод doPost, который в свою очередь:
    
    - если введенные данные совпали хотя бы с одной записью в базе данных перенаправляет пользователя на сервелет (см. [AirportSimulatorTwo/Servlet/FlightServletToJstl.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Servlet/FlightServletToJstl.java)), который отображает список перелетов;
    - если в базе данных нет соответствия с введенными данными, то происходит повторное перенаправление пользователя на сервелет аутентификации (см. [Authentication/LogInServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authentication/LogInServlet.java)), при этом в параметрах передается уже введенный email и сообщение, что данные введены неверно.

Вся магия начинается, когда мы должны получить данные из базы (т.е. найти нашего пользователя по email и password, если такой существует в базе). Помним что при работе с архитектурой MVC у нас существуют слои по которым перемещаются запросы от пользователя и ответы от приложения:
  
  - На уровне общения с базой данный DAO, у нас уже есть класс UserDao (он имеет активный метод *.save() для сохранения пользователя в базе). Теперь этот класс должен позволить найти в базе данных, по введенным полям email и password возможного пользователя. Значит нам нужен метод который позволит найти и сопоставить имеющиеся и полученные данные (см. *.findByEmailAndPassword(String email, String password));
  - Добавим на уровень DTO класс [ReadUserDto.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/DTO/ReadUserDto.java), который будет использоваться когда нам понадобиться получать полные данные о пользователе из базы данных;
  - Теперь нам нужно "подняться" на уровень Service - ов, который обрабатывает запросы от сервлетов. Именно на этом уровне будет создан метод public Optional<ReadUserDto> login(String email, String password), который обращаясь к уровню DAO постарается получить ответ из базы данных;
  - Для возвращения ответа на запрос пользователя, нам понадобится преобразование полученных из базы данных в удобный (необходимый) для отображения вид, создаем класс [AirportSimulatorTwo/Mapper/ReadUserMapper.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Mapper/ReadUserMapper.java) у которого один единственный метод public ReadUserDto mapFrom(User object), т.е. данные с одного уровня перешли на другой (или один объект превращен в другой).

Для наглядности, что данные введены неверно (пользователь в базе не найден), в форме аутентификации login.jsp добавим обработку параметра 'error' переданного сервлетом LogInServlet.java из метода *.onLoginFail(req, resp).

### Часть 5 - Выход из WEB - приложения (LOGOUT)

Для реализации данного функционала мы создадим сервлет [LogOutServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authentication/LogOutServlet.java) в котором будет переопределен один метод doPost т.к. мы не пытаемся запросить что-либо у сервера, а хотим внести изменения в состояние текущей сессии.

После того как изменения в состояние текущей сессии внесены, в нашем случае мы использовали метод *.invalidate() - т.е. удалили текущую сессию из ассоциативного массива, который хранится у нас на сервере. Сессия становится невалидной и нам придется заново 'логиниться'.

Но для доступа к такому, вроде бы простому функционалу нам нужна форма взаимодействия с пользователем (например, простая кнопка 'LOGOUT'):
  
  - В наш файл [header.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/header.jsp) добавим нужный 'taglib' и код, который позволит после нажатия соответствующей кнопки перейти на сервлет с мапингом '/logout' и отработать удаление текущей сессии. В идеале данный 'хедер' должен быть доступен с любой страницы сайта (из любого места web-приложения);
  - В коде кнопки 'LOGOUT' предусматриваем простую проверку на наличие пользователя в текущей сессии и если таковой есть отображаем ее, если нет кнопка на экране не видна, сама форма отсылается на сервлет [LogOutServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authentication/LogOutServlet.java) методом POST.

### Часть 6 - Авторизация

Проверяем полномочия пользователя на совершения определенных действий и доступа к определенным разделам сайта (web-приложения).

Подобные проверки можно проводить используя web-фильтры:
  
  - Создадим фильтр [Authorization/AuthorizationFilter.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authorization/AuthorizationFilter.java) с параметром ("/*"), т.е. с учетом настроек нашего TomCat сервера, под этот фильтр попадут все наши страницы;
  - Однако часть из них, как минимум две страницы, это сервлеты [LogInServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authentication/LogInServlet.java) и [UserRegForm.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/AirportSimulatorTwo/Servlet/UserRegForm/UserRegForm.java) с мапингом: "/login" и "/registration" соответственно, будут доступны при любых условиях.

### Часть 7 - Локализация и интернационализация.

Класс [Locale/LocaleDemo.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Locale/LocaleDemo.java) простая демонстрация использования локализации без использования TomCat сервера. 

Для более серьезного использования настроек локализации используем папку '[resources](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/tree/master/MVCPracticeAdvanced/resources)' и так называемые [Resource Bundle](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/DOC/LOCALE/ResourceBundle.txt):
[translations.properties](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/resources/translations.properties) (ENG) и [translations_ru_RU.properties](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/resources/translations_ru_RU.properties) (RUS), где зададим соответствующие настройки языков для наших форм взаимодействия с пользователем.

Создадим файл [international_login.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/international_login.jsp) для внедрения преимуществ интернационализации.

В наших *.JSP файлах подключаем необходимые JSTL теги, а если точнее: <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> и тогда у нас появляется возможность использовать 'setLocale' и 'setBundle'. Пример в комментариях [international_login.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/international_login.jsp).

Создадим файл [set_local.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/set_local.jsp) куда интегрируем необходимый код, а затем мы сможем вставить этот файл в любую *.JSP страницу нашего проекта и воспользоваться преимуществом интернационализации.

В файле international_login.jsp хардкод поля (см. пример login.jsp) заменяем на ключи файлов свойств (см. translations_xx_XX.properties)

Нам необходимо дать пользователю возможность выбора языка:
  
  - Создаем файл [LocaleServlet](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Locale/LocaleServlet.java) в котором переназначим метод doPost, он и будет устанавливать языковые параметры наших страниц (меню и т.д.);
  - Сами кнопки выбора RU или ENG разместим в футере (см. [footer.jsp](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/web/WEB-INF/jsp/footer.jsp)), который прицепим к нужным страницам тегом INCLUDE, с этой страницы методом POST мы передаем значение 'value' нашему серверу и он проводит необходимые изменения в отображаемых данных (response);
  - В файле [AuthorizationFilter.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Authorization/AuthorizationFilter.java) добавляем маппинг "/locale" в константу PUBLIC_PATH на наш сервлет отвечающий за локализацию [LocaleServlet.java](https://github.com/JcoderPaul/HTTP_Servlets_Java_EE/blob/master/MVCPracticeAdvanced/src/Locale/LocaleServlet.java).