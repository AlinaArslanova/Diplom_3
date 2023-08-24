import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.stellarburgers.User;
import ru.yandex.stellarburgers.UserMethods;
import ru.yandex.stellarburgers.UserCredentials;
import ru.yandex.stellarburgers.LoginPage;
import ru.yandex.stellarburgers.MainPage;
import ru.yandex.stellarburgers.RegistrationPage;
import ru.yandex.stellarburgers.UserGenerator;
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
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
    }
    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistration() {
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
        System.out.println("Удален - " + accessToken);
    }
    @Test
    @DisplayName("Ошибка некорректного пароля")
    public void checkRegistrationWithIncorrectPassword() {
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