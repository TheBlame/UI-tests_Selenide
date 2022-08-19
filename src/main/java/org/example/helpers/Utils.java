package org.example.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Utils {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    private static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(BASE_URI)
                    .build();

    public static void deleteUser(String token) {
        if (token != null) {
            given()
                    .baseUri(BASE_URI)
                    .auth()
                    .oauth2(token.substring(7))
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

    public static Map<String, String> getTokens(String email, String password) {
        String request = "{\"email\": \"" + email + "\", " +
                "\"password\": \"" + password + "\"}";
        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .body(request)
                .post("/api/auth/login");
        String accessToken = response
                .then()
                .extract()
                .path("accessToken");
        String refreshToken = response
                .then()
                .extract()
                .path("refreshToken");
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public static void registerUser(String name, String email, String password) {
        String request = "{\"name\": \"" + name + "\", " +
                "\"email\": \"" + email + "\", " +
                "\"password\": \"" + password + "\"}";
        given()
                .spec(REQUEST_SPECIFICATION)
                .body(request)
                .when()
                .post("/api/auth/register");
    }
}
