import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.User;
import stellarburgers.UserMethods;
import stellarburgers.UserCredentials;
import stellarburgers.LoginPage;
import stellarburgers.MainPage;
import stellarburgers.RegistrationPage;
import stellarburgers.UserGenerator;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationUserTests extends Main {

    private String accessToken;
    private User user;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistration() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        UserMethods userMethods = UserGenerator.getUser();
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.setFieldsForRegistration(userMethods.getName(), userMethods.getEmail(), userMethods.getPassword());
        UserCredentials userCredentials = new UserCredentials(userMethods.getEmail(), userMethods.getPassword());
        ValidatableResponse loginResponse = user.loginUser(userCredentials);
        loginResponse.assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
        accessToken = loginResponse.extract().path("accessToken");
        user.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Ошибка некорректного пароля")
    public void checkRegistrationWithIncorrectPassword() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        UserMethods userMethods = UserGenerator.getIncorrectPasswordUser();
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.setFieldsForRegistration(userMethods.getName(), userMethods.getEmail(), userMethods.getPassword());
        registrationPage.getTextError();
        UserCredentials userCredentials = new UserCredentials(userMethods.getEmail(), userMethods.getPassword());
        ValidatableResponse loginResponse = user.loginUser(userCredentials);
        loginResponse.assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}