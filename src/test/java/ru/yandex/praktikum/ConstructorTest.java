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

public class ConstructorTest {
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
    @DisplayName("Проверка перехода в раздел булочек")
    @Description("Позитивный кейс проверки перехода в раздел булочек на главной странице")
    public void checkOpeningBuns() {

        MainPage main = new MainPage(driver);

        main.openPage();
        main.clickSaucesPointer();
        main.clickBunsPointer();
        main.getAttributeBunsPointerParent();
        boolean isAttributeContainsCurrent = main.getAttributeBunsPointerParent().contains("current");
        Assert.assertTrue("Переход к разделу не произошел", isAttributeContainsCurrent);
    }

    @Test
    @DisplayName("Проверка перехода в раздел соусов")
    @Description("Позитивный кейс проверки перехода в раздел соусов на главной странице")
    public void checkOpeningSauces() {

        MainPage main = new MainPage(driver);

        main.openPage();
        main.clickSaucesPointer();
        main.getAttributeSaucesPointerParent();
        boolean isAttributeContainsCurrent = main.getAttributeSaucesPointerParent().contains("current");
        Assert.assertTrue("Переход к разделу не произошел", isAttributeContainsCurrent);
    }

    @Test
    @DisplayName("Проверка перехода в раздел начинки")
    @Description("Позитивный кейс проверки перехода в раздел начинок на главной странице")
    public void checkOpeningFillings() {

        MainPage main = new MainPage(driver);

        main.openPage();
        main.clickFillingsPointer();
        main.getAttributeFillingsPointerParent();
        boolean isAttributeContainsCurrent = main.getAttributeFillingsPointerParent().contains("current");
        Assert.assertTrue("Переход к разделу не произошел", isAttributeContainsCurrent);
    }
}
