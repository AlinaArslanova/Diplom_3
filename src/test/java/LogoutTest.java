import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import stellarburgers.User;
import stellarburgers.UserMethods;
import stellarburgers.UserGenerator;
import stellarburgers.AccountPage;
import stellarburgers.LoginPage;
import stellarburgers.MainPage;

public class LogoutTest extends Main {

    private User user;
    private UserMethods userMethods;

    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    @DisplayName("Выход по кнопке <Выйти> в личном кабинете")
    public void accountExitButtonTest() {

        String accessToken;
        user = new User();
        userMethods = UserGenerator.getUser();
        ValidatableResponse createResponse = user.createUser(userMethods);
        accessToken = createResponse.extract().path("accessToken");
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickPersonalAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(userMethods.getEmail(), userMethods.getPassword());
        loginPage.clickSingInButton();
        mainPage.clickPersonalAccountButton();
        accountPage = new AccountPage(webDriver);
        accountPage.clickLogOutButton();
        loginPage.isSingInButtonDisplayed();
        user.deleteUser(accessToken);
    }
}