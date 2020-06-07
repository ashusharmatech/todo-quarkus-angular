package io.todo.resources;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;
import io.todo.dto.UserLoginDto;
import io.todo.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
class UserResourceTest {
    private static User user;
    private static UserLoginDto userLoginDto;
    private static Header header;

    @BeforeAll
    public static void setup() {
        user = new User();
        user.username = "ashu";
        user.firstName = "ashu";
        user.lastName = "sharma";
        user.password = "password";
        userLoginDto = new UserLoginDto();
        userLoginDto.setUsername(user.username);
        userLoginDto.setPassword(user.password);

        header = new Header("Content-Type","application/json; charset=utf8");
    }

    @Test
    void test_register() {
        given()
                .when()
                .header(header)
                .body(user)
                .post("/users/register")
                .then()
                .statusCode(201).assertThat().contentType("application/json").and().body("username",is(user.username));

    }

    @Test
    void testLogin() {
        given()
                .when()
                .header(header)
                .body(userLoginDto)
                .post("/users/login")
                .then()
                .statusCode(200).assertThat().contentType("application/json").and().body("username",is(user.username));
    }
}