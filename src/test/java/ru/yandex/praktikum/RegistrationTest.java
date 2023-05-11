package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.model.api.User;
import ru.yandex.praktikum.model.api.UserClient;
import ru.yandex.praktikum.model.api.UserCredentials;
import ru.yandex.praktikum.model.api.UserGenerator;

public class RegistrationTest {
    private UserClient userClient;
    private UserCredentials credentials;
    private String accessToken;
    private WebDriver driver;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }
    @Before
    public void startUp() {
        // открыть браузер
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // создать пользователя
        userClient = new UserClient();
        User user = UserGenerator.getRandom();
        userClient.createUser(user);
        //получить токен для удаления пользователя
        accessToken = userClient.login(UserCredentials.from(user))
                .extract().path("accessToken");
        credentials = UserCredentials.from(user);
    }
    @After
    public void teardown() {
        // Удалить пользователя
        userClient.delete(accessToken);
        // Закрыть браузер
        driver.quit();
    }
    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Позитивный кейс регистрации пользователя с корретными данными")
    public void checkRegistrationWithCorrectData() {
        RegistrationPage registration = new RegistrationPage(driver);
        LogInPage logIn = new LogInPage(driver);
        MainPage main = new MainPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);

        registration.openPage();
        registration.setName(credentials.getName());
        registration.setEmail(credentials.getEmail());
        registration.setPassword(credentials.getPassword());
        registration.clickRegistrationButton();
        logIn.openPage();
        logIn.setEmail(credentials.getEmail());
        logIn.setPassword(credentials.getPassword());
        logIn.clickLogInButton();
        main.waitForOpenPage();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
    @Test
    @DisplayName("Регистрация пользователя c некорректным паролем")
    @Description("Негативный кейс регистрации пользователя с корретными данными")
    public void checkRegistrationWithIncorrectData() {
        RegistrationPage registration = new RegistrationPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomIncorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        Assert.assertTrue("Сообщение о некорректном пароле не отображается", registration.isIncorrectPasswordTitleDisplayed());
    }
}