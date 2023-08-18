import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import stellarburgers.User;
import stellarburgers.UserMethods;
import stellarburgers.UserGenerator;
import stellarburgers.AccountPage;
import stellarburgers.LoginPage;
import stellarburgers.MainPage;

public class AccountTests extends Main {

    private User user;
    private UserMethods userMethods;
    private String accessToken;

    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    @DisplayName("Переход по клику на <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickPersonalAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.isSingInButtonDisplayed();
    }

    @Test
    @DisplayName("Переход по клику на <Конструктор>")
    public void checkGoToConstructor() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        user = new User();
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.clickPersonalAccountButton();
        accountPage = new AccountPage(webDriver);
        accountPage.clickCreateBurgerButton();
        mainPage.isOrderButtonDisplayed();
        user.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на логотипу Stellar Burgers")
    public void checkOutFromPersonalAccount() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        user = new User();
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.clickPersonalAccountButton();
        accountPage = new AccountPage(webDriver);
        accountPage.clickLogoStellarBurgerButton();
        mainPage.isOrderButtonDisplayed();
        user.deleteUser(accessToken);
    }
}