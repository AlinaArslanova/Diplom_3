package ru.yandex.stellarburgers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    private By nameFieldInput = By.xpath("//label[text()='Имя']//following-sibling::input");
    private By emailFieldInput = By.xpath("//label[text()='Email']//following-sibling::input");
    private By passwordFieldInput = By.xpath("//input[@type='password']");
    private By registerButton = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private By singInButton = By.xpath("//a[text()='Войти']");
    private By textError = By.xpath(".//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Заполнение поля Имя")
    public void setName(String name) {
        driver.findElement(nameFieldInput).sendKeys(name);
    }
    @Step("Заполнение поля Email")
    public void setEmail(String email) {
        driver.findElement(emailFieldInput).sendKeys(email);
    }
    @Step("Заполнение поля Пароль")
    public void setPassword(String password) {
        driver.findElement(passwordFieldInput).sendKeys(password);
    }
    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    @Step("Регистрация")
    public void setFieldsForRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
    @Step("Проверка отображения ошибки некорректного пароля")
    public boolean getTextError() {
        return driver.findElement(textError).isDisplayed();
    }
    @Step("Нажатие кнопки Войти")
    public void clickSingInButton() {
        driver.findElement(singInButton).click();
    }
}