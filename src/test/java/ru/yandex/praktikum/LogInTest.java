package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.model.*;
import ru.yandex.praktikum.model.api.User;
import ru.yandex.praktikum.model.api.UserClient;
import ru.yandex.praktikum.model.api.UserCredentials;
import ru.yandex.praktikum.model.api.UserGenerator;

public class LogInTest {
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
    @DisplayName("Проверка входа на главной странице с помощью кнопки Войти в аккаунт")
    @Description("Позитивный кейс входа с корректными данными")
    public void checkLogInWithAccountButton() {
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);

        main.openPage();
        main.clickLogInButton();
        logIn.setEmail(credentials.getEmail());
        logIn.setPassword(credentials.getPassword());
        logIn.clickLogInButton();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
    @Test
    @DisplayName("Проверка входа на главной странице с помощью кнопки Личный кабинет")
    @Description("Позитивный кейс входа с корректными данными через кнопку Личный кабинет на главной странице")
    public void checkLogInWithPersonalAccountButton() {
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);

        main.openPage();
        main.clickPersonalAccountButton();
        logIn.setEmail(credentials.getEmail());
        logIn.setPassword(credentials.getPassword());
        logIn.clickLogInButton();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа со страницы Регистрация")
    @Description("Позитивный кейс входа с корректными данными со страницы регистрации")
    public void checkLogInWithRegistrationPageButton() {
        RegistrationPage registration = new RegistrationPage(driver);
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);

        registration.openPage();
        registration.setName(credentials.getName());
        registration.setEmail(credentials.getEmail());
        registration.setPassword(credentials.getPassword());
        registration.clickRegistrationButton();
        registration.openPage();
        registration.clickLogInButton();
        logIn.setEmail(credentials.getEmail());
        logIn.setPassword(credentials.getPassword());
        logIn.clickLogInButton();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
    @Test
    @DisplayName("Проверка входа со страницы Восстановление пароля")
    @Description("Позитивный кейс входа с корректными данными со станицы восстановления пароля")
    public void checkLogInWithPasswordRecoveryPage() {
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        PasswordRecoveryPage passwordRecovery = new PasswordRecoveryPage(driver);

        passwordRecovery.openPage();
        passwordRecovery.clickLogInButton();
        logIn.setEmail(credentials.getEmail());
        logIn.setPassword(credentials.getPassword());
        logIn.clickLogInButton();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
}