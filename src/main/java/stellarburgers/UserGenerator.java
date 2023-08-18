package stellarburgers;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static UserMethods getUser() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphabetic(8) + "@new.ru";
        String password = RandomStringUtils.randomAlphabetic(8);
        return new UserMethods(name, email, password);
    }

    public static UserMethods getIncorrectPasswordUser() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphabetic(8) + "@new.ru";
        String password = RandomStringUtils.randomAlphabetic(5);
        return new UserMethods(name, email, password);
    }
}