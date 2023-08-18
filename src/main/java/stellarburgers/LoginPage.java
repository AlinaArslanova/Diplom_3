package stellarburgers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private By singInButton = By.xpath(".//button[contains(text(),'Войти')]");
    private By emailInput = By.xpath(".//input[@name='name']");
    private By passwordInput = By.xpath(".//input[@name='Пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы входа")
    public void setLoginForm(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие кнопки зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие кнопки Войти")
    public void clickSingInButton() {
        driver.findElement(singInButton).click();
    }

    @Step("Нажатие кнопки восстановить пароль")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Проверка отображения кнопки Войти")
    public boolean isSingInButtonDisplayed() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOf(driver.findElement(singInButton)));
        return driver.findElement(singInButton).isDisplayed();
    }
}