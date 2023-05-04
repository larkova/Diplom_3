package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.*;

public class LogOutTest {
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
    @DisplayName("Проверка выхода из учетной записи")
    @Description("Позитивный кейс выхода со страницы учетной записи")
    public void checkLogout() {
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
        personalAccount.clickLogoutButton();
        login.waitForOpenPage();

        Assert.assertTrue("Выход не произошел", login.isLogInTitleDisplayed());
    }
}
