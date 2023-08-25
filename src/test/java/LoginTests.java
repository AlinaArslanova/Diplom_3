import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.stellarburgers.User;
import ru.yandex.stellarburgers.UserMethods;
import ru.yandex.stellarburgers.LoginPage;
import ru.yandex.stellarburgers.MainPage;
import ru.yandex.stellarburgers.RecoverPasswordPage;
import ru.yandex.stellarburgers.RegistrationPage;
import ru.yandex.stellarburgers.UserGenerator;

public class LoginTests extends Main {
    private User user;
    private UserMethods userMethods;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registerPage;
    RecoverPasswordPage recoverPasswordPage;
    String accessToken;
    @Before
    public void setUp() {
        user = new User();
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
    }
    @After
    public void deleteUser() {
        user.deleteUser(accessToken);
        System.out.println("Удален - " + accessToken);
    }
    @Test
    @DisplayName("Вход через кнопку <Войти в аккаунт> на главной")
    public void loginUserLoginButton() {
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }
    @Test
    @DisplayName("Вход через кнопку <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        mainPage.clickPersonalAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginUserRegistrationForm() {
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();
        registerPage = new RegistrationPage(webDriver);
        registerPage.clickSingInButton();
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginUserRecoverPasswordButton() {
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.clickRecoverPasswordButton();
        recoverPasswordPage = new RecoverPasswordPage(webDriver);
        recoverPasswordPage.clickLoginButton();
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }
}
