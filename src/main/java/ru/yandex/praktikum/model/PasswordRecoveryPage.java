package ru.yandex.praktikum.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {

    private WebDriver driver;
    public PasswordRecoveryPage (WebDriver driver) {
        this.driver = driver;
    }
    //метод открытия страницы
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a")));
    }

    // Локатор кнопки Войти
    private By logInButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    // Метод клика по кнопке Войти
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
}