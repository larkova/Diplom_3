package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class RegistrationTest {
    private WebDriver driver;
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Позитивный кейс регистрации пользователя с корретными данными")
    public void checkRegistrationWithCorrectData() {
        RegistrationPage registration = new RegistrationPage(driver);
        LogInPage login = new LogInPage(driver);
        MainPage main = new MainPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomCorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        login.openPage();
        login.setEmail(dataForRegistration.email);
        login.setPassword(dataForRegistration.password);
        login.clickLogInButton();
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
        LogInPage login = new LogInPage(driver);
        MainPage main = new MainPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomIncorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        Assert.assertTrue("Сообщение о некорректном пароле не отображается", registration.isIncorrectPasswordTitleDisplayed());
    }
}
