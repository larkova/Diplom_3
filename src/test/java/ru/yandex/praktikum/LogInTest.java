package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.model.*;

public class LogInTest {
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
    @DisplayName("Проверка входа на главной странице с помощью кнопки Войти в аккаунт")
    @Description("Позитивный кейс входа с корректными данными")
    public void checkLogInWithAccountButton() {

        RegistrationPage registration = new RegistrationPage(driver);
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomCorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        main.openPage();
        main.clickLogInButton();

        logIn.setEmail(dataForRegistration.email);
        logIn.setPassword(dataForRegistration.password);
        logIn.clickLogInButton();

        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
    @Test
    @DisplayName("Проверка входа на главной странице с помощью кнопки Личный кабинет")
    @Description("Позитивный кейс входа с корректными данными через кнопку Личный кабинет на главной странице")
    public void checkLogInWithPersonalAccountButton() {

        RegistrationPage registration = new RegistrationPage(driver);
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomCorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        main.openPage();
        main.clickPersonalAccountButton();

        logIn.setEmail(dataForRegistration.email);
        logIn.setPassword(dataForRegistration.password);
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
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomCorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();
        registration.openPage();
        registration.clickLogInButton();

        logIn.setEmail(dataForRegistration.email);
        logIn.setPassword(dataForRegistration.password);
        logIn.clickLogInButton();

        main.clickPersonalAccountButton();

        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
    @Test
    @DisplayName("Проверка входа со страницы Восстановление пароля")
    @Description("Позитивный кейс входа с корректными данными со станицы восстановления пароля")
    public void checkLogInWithPasswordRecoveryPage() {
        RegistrationPage registration = new RegistrationPage(driver);
        MainPage main = new MainPage(driver);
        LogInPage logIn = new LogInPage(driver);
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        PasswordRecoveryPage passwordRecovery = new PasswordRecoveryPage(driver);
        DataForRegistration dataForRegistration = DataForRegistrationGenerator.getRandomCorrectData();

        registration.openPage();
        registration.setName(dataForRegistration.name);
        registration.setEmail(dataForRegistration.email);
        registration.setPassword(dataForRegistration.password);
        registration.clickRegistrationButton();

        passwordRecovery.openPage();
        passwordRecovery.clickLogInButton();

        logIn.setEmail(dataForRegistration.email);
        logIn.setPassword(dataForRegistration.password);
        logIn.clickLogInButton();

        main.clickPersonalAccountButton();

        personalAccount.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в ЛК", personalAccount.isLogoutButtonDisplayed());
    }
}