import io.restassured.response.Response;

import static configs.AppConfig.*;
import static io.restassured.RestAssured.given;
public class UserController {
    private static final String HEADER = "Content-type";
    private static final String HEADER_TYPE = "application/json";

    public static void newUser(createUser createUser) {
        given()
                .header(HEADER, HEADER_TYPE)
                .body(createUser)
                .when()
                .post(REGISTER_PATH);

    }
    public static void deleteUser(String token) {
        given()
                .header(HEADER, HEADER_TYPE)
                .auth().oauth2(token)
                .when()
                .delete(USER_PATH);
    }

    public static String getUserToken(loginUser loginUser) {
        Response response = login(loginUser);
        String accessToken = response.jsonPath().get("accessToken");
        return accessToken.replace("Bearer ","");
    }

    public static Response login(loginUser loginUser) {
        return
                given()
                        .header(HEADER, HEADER_TYPE)
                        .body(loginUser)
                        .when()
                        .post(LOGIN_PATH);
    }
}
