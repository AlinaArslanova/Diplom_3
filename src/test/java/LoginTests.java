import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.User;
import stellarburgers.UserMethods;
import stellarburgers.LoginPage;
import stellarburgers.MainPage;
import stellarburgers.RecoverPasswordPage;
import stellarburgers.RegistrationPage;
import stellarburgers.UserGenerator;

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
    }
    @After
    public void deleteUser() {
        user.deleteUser(accessToken);
        System.out.println("Удален - " + accessToken);
    }
    @Test
    @DisplayName("Вход через кнопку <Войти в аккаунт> на главной")
    public void loginUserLoginButton() {
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickPersonalAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.isOrderButtonDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginUserRegistrationForm() {
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
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
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
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
