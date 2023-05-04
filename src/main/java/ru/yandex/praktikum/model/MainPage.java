package ru.yandex.praktikum.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //метод открытия главной страницы
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    // метод ожидания загрузки страницы
    public void waitForOpenPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/header/nav/a/p")));
    }
    // Локатор для кнопки Войти в аккаунт
    private By logInButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button");

    // Локатор для кнопки Личный кабинет
    private By personalAccountButton = By.xpath("//*[@id='root']/div/header/nav/a/p");

    // Локатор для надписи Соберите бургер
    private By setBuggerTitle = By.xpath("//*[@id='root']/div/main/section[1]/h1");

    // Локаторы для указателя Булки в Конструкторе,
    private By bunsPointer = By.xpath(".//span[text()='Булки']");
    private By bunsPointerParent = By.xpath(".//span[text()='Булки']/parent::div");

    // Локаторы для указателя Соусы в Конструкторе
    private By saucesPointer = By.xpath(".//span[text()='Соусы']");
    private By saucesPointerParent = By.xpath(".//span[text()='Соусы']/parent::div");

    // Метод клика по кнопке Личный кабинет
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    // Метод клика по кнопке Войти в аккаунт
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }

    // Метод проверки отображения надписи Соберите бургер
    public boolean isSetBuggerTitleDisplayed() {
        return driver.findElement(setBuggerTitle).isDisplayed();
    }

    // Метод клика по указателю Булки
    public void clickBunsPointer() {
        driver.findElement(bunsPointer).click();
    }

    // Метод получения атрибута родителя указателя Булки
    public String getAttributeBunsPointerParent() {
        return driver.findElement(bunsPointerParent).getAttribute("class");
    }

    // Метод клика по указателю Соусы
    public void clickSaucesPointer() {
        driver.findElement(saucesPointer).click();
    }

    // Метод получения атрибута родителя указателя Соусы
    public String getAttributeSaucesPointerParent() {
        return driver.findElement(saucesPointerParent).getAttribute("class");
    }

    // Метод клика по указателю Начинки
    public void clickFillingsPointer() {
        driver.findElement(saucesPointer).click();
    }

    // Метод получения атрибута родителя указателя Начинки
    public String getAttributeFillingsPointerParent() {
        return driver.findElement(saucesPointerParent).getAttribute("class");
    }
}
