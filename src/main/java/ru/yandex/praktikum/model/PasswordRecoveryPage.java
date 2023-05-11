package ru.yandex.praktikum.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.model.constants.Uri.PASSWORD_RECOVERY_PAGE;

public class PasswordRecoveryPage {

    private WebDriver driver;

    // Локатор кнопки Войти
    private By logInButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    // Конструктор класса
    public PasswordRecoveryPage (WebDriver driver) {
        this.driver = driver;
    }

    //метод открытия страницы
    public void openPage() {
        driver.get(PASSWORD_RECOVERY_PAGE);
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a")));
    }

    // Метод клика по кнопке Войти
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
}