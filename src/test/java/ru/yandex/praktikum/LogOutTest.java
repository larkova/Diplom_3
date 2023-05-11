package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import okhttp3.Credentials;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.*;
import ru.yandex.praktikum.model.api.User;
import ru.yandex.praktikum.model.api.UserClient;
import ru.yandex.praktikum.model.api.UserCredentials;
import ru.yandex.praktikum.model.api.UserGenerator;


public class LogOutTest {
    private User user;
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
    @DisplayName("Проверка выхода из учетной записи")
    @Description("Позитивный кейс выхода со страницы учетной записи")
    public void checkLogout() {
        LogInPage login = new LogInPage(driver);
        MainPage main = new MainPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);

        login.openPage();
        login.setEmail(credentials.getEmail());
        login.setPassword(credentials.getPassword());
        login.clickLogInButton();
        main.waitForOpenPage();
        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();
        personalAccount.clickLogoutButton();
        login.waitForOpenPage();

        Assert.assertTrue("Выход не произошел", login.isLogInTitleDisplayed());
    }
}
