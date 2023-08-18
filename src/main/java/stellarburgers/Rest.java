package stellarburgers;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
public class Rest {
        private static String BASE_URL = "https://stellarburgers.nomoreparties.site/";

        public RequestSpecification getBaseSpec() {
            return new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .setContentType(ContentType.JSON)
                    .build();
        }
    }
