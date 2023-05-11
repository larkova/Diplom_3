package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.model.constants.Uri.REGISTRATION_PAGE;

public class RegistrationPage {

    private WebDriver driver;

    // Локатор поля Имя
    private By nameInput = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");

    // Локатор поля Email
    private By emailInput = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");

    // Локатор поля Пароль
    private By passwordInput = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/div/input");

    // Локатор кнопки Зарегистрироваться
    private By registrationButton = By.xpath("//*[@id='root']/div/main/div/form/button");

    // Локатор кнопки Войти
    private By logInButton = By.xpath("//*[@id='root']/div/main/div/div/p/a");

    // Локатор надписи Некорректный пароль
    private By incorrectPasswordTitle = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");

    // Конструктор класса
    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    //метод открытия страницы
    public void openPage() {
        driver.get(REGISTRATION_PAGE);
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input")));
    }

    // Метод заполнения поля Имя
    public void setName (String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    // Метод заполнения поля Email
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Метод заполнения поля Password
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
     }

    // Метод клика по кнопке Зарегистрироваться
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    // Метод клика по кнопке Войти
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }

    // Метод проверки отображения надписи Некорректный пароль
    public boolean isIncorrectPasswordTitleDisplayed() {
        return driver.findElement(incorrectPasswordTitle).isDisplayed();
    }
}
