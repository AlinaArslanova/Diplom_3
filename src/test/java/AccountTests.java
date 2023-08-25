import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.stellarburgers.User;
import ru.yandex.stellarburgers.UserMethods;
import ru.yandex.stellarburgers.UserGenerator;
import ru.yandex.stellarburgers.AccountPage;
import ru.yandex.stellarburgers.LoginPage;
import ru.yandex.stellarburgers.MainPage;

public class AccountTests extends Main {
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Before
    public void setUp() {
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
    }
    @After
    public void deleteUser() {
        user.deleteUser(accessToken);
        System.out.println("Удален - " + accessToken);
    }
    @Test
    @DisplayName("Переход по клику на <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        accountPage.isLogOutButtonDisplayed();
    }
    @Test
    @DisplayName("Переход по клику на <Конструктор>")
    public void checkGoToConstructor() {
        accountPage.clickCreateBurgerButton();
        mainPage.isOrderButtonDisplayed();
    }
    @Test
    @DisplayName("Переход по клику по логотипу Stellar Burgers")
    public void checkOutFromPersonalAccount() {
        accountPage.clickLogoStellarBurgerButton();
        mainPage.isOrderButtonDisplayed();
    }
}