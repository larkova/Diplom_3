package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.model.*;

public class OpenConstructorFromPersonalAccountPageTest {
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
    @DisplayName("Проверка перехода на Главную страницу из Личного кабинета")
    @Description("Позитивный кейс перехода из Личного кабинета на Главную страницу")
    public void checkOpeningConstructorFromPersonalAccount() {
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

        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();
        personalAccount.clickConstructorButton();
        main.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в Конструктор", main.isSetBuggerTitleDisplayed());
    }
    @Test
    @DisplayName("Проверка перехода со страницы Личного кабинета на Главную страницу")
    @Description("Позитивный кейс перехода на главную страницу из Личного кабинета с помощью кнопки-логотипа Stellar Burgers")
    public void checkTransitWithLogoStellarBurgers() {
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

        main.clickPersonalAccountButton();
        personalAccount.waitForOpenPage();;
        personalAccount.clickLogoStellarBurgers();
        main.waitForOpenPage();

        Assert.assertTrue("Не произошел переход в Конструктор", main.isSetBuggerTitleDisplayed());
    }
}
