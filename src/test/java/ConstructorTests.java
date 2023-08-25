import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.stellarburgers.MainPage;
import static org.junit.Assert.*;

public class ConstructorTests extends Main {
    MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
    }
    @Test
    @DisplayName("Переход к разделу <Соусы>")
    public void checkGoToSauces() {
        mainPage.clickSauceTab();
        assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Переход к разделу <Начинки>")
    public void checkGoToFillings() {
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Переход к разделу <Булки>")
    public void checkGoToBuns() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }
}