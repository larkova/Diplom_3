package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
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
    private String accessToken;
    private WebDriver driver;
    private User user = UserGenerator.getRandom();
    private UserGenerator userGenerator = new UserGenerator();

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
    }
    @After
    public void teardown() {
        //Удалить пользователя
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
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
        userClient = new UserClient();

        registration.openPage();
        registration.setName(user.getName());
        registration.setEmail(user.getEmail());
        registration.setPassword(user.getPassword());
        registration.clickRegistrationButton();
        logIn.openPage();
        logIn.setEmail(user.getEmail());
        logIn.setPassword(user.getPassword());
        logIn.clickLogInButton();
        main.waitForOpenPage();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
        personalAccount.clickLogoutButton();

        ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");

    }
    @Test
    @DisplayName("Регистрация пользователя c некорректным паролем")
    @Description("Негативный кейс регистрации пользователя с корретными данными")
    public void checkRegistrationWithIncorrectData() {
        RegistrationPage registration = new RegistrationPage(driver);
        userClient = new UserClient();

        registration.openPage();
        registration.setName(user.getName());
        registration.setEmail(user.getEmail());
        registration.setPassword(user.setPassword("123"));
        registration.clickRegistrationButton();

        Assert.assertTrue("Сообщение о некорректном пароле не отображается", registration.isIncorrectPasswordTitleDisplayed());

        ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");
    }
}