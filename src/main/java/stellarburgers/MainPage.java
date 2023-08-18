package stellarburgers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By setOrderButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']" +
            "[text() = 'Оформить заказ']");
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By bunsTab = By.xpath(".//div[./span[text()='Булки']]");
    private By sauceTab = By.xpath(".//div[./span[text()='Соусы']]");
    private By fillingsTab = By.xpath(".//div[./span[text()='Начинки']]");
    private By currentMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы сайта")
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Нажатие кнопки Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Личный кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public void isOrderButtonDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(setOrderButton));
    }

    @Step("Нажатие кнопки Соусы")
    public void clickSauceTab() {
        driver.findElement(sauceTab).click();
    }

    @Step("Нажатие кнопки Начинки")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Нажатие кнопки Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Проверка текста текущего меню")
    public String getTextFromSelectedMenu() {
        return driver.findElement(currentMenu).getText();
    }
}