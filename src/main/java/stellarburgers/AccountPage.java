package stellarburgers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private WebDriver driver;
    private By createBurgerButton = By.xpath("//p[text()='Конструктор']");
    private By logoStellarBurgerButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    private By logOutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие кнопки Конструктор")
    public void clickCreateBurgerButton() {
        driver.findElement(createBurgerButton).click();
    }

    @Step("Нажатие логотипа Stellar Burgers")
    public void clickLogoStellarBurgerButton() {
        driver.findElement(logoStellarBurgerButton).click();
    }

    @Step("Нажатие кнопки Выйти")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}