package ru.yandex.praktikum.model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {

    private WebDriver driver;

    // Локатор кнопки Профиль
    private By profileButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[1]/a");

    // Локатор кнопки Конструктор
    private By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p");

    // Локатор логотипа Stellar Burgers
    private By logoStellarBurgers = By.xpath("//*[@id='root']/div/header/nav/div");

    // Локатор кнопки Выход
    private By logoutButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    // Конструктор класса
    public PersonalAccountPage (WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button")));
    }

    // Метод проверки отображения кнопки Выход
    public boolean isLogoutButtonDisplayed() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    // Метод клика по кнопке Конструктор
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    // Метод клика по логотипу Stellar Burgers
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }

    // Метод клика по кнопке Выход
    public void clickLogoutButton () {
        driver.findElement(logoutButton).click();
    }
}
