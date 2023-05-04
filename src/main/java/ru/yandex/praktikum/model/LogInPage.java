package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

    private WebDriver driver;
    public LogInPage (WebDriver driver) {
        this.driver = driver;
    }
    //метод открытия страницы
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div/h2")));
    }

    // Локатор поля Email
    private By emailInput = By.xpath(".//div/input[@name='name']");

    // Локатор поля Пароль
    private By passwordInput = By.xpath(".//div/input[@name='Пароль']");

    // Локатор кнопки Войти
    private By LogInButton = By.xpath(".//button[text()='Войти']");

    // Локатор надписи Вход
    private By logInTitle = By.xpath("//*[@id='root']/div/main/div/h2");


    // Метод заполнения поля Email
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Метод заполнения поля Password
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Метод клика по кнопке Войти
    public void clickLogInButton() {
        driver.findElement(LogInButton).click();
    }

    // Метод проверки отображения надписи вход
    public boolean isLogInTitleDisplayed() {
        return driver.findElement(logInTitle).isDisplayed();
    }
}
