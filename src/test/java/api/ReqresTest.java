package api;

import api.pojo.colorsPojo.ColorsData;
import api.pojo.loginPojo.Login;
import api.pojo.loginPojo.SuccessLogin;
import api.pojo.loginPojo.UnsuccessLogin;
import api.pojo.registerPojo.Register;
import api.pojo.registerPojo.SuccessReg;
import api.pojo.registerPojo.UnSuccessReg;
import api.pojo.userPojo.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String URL = "https://reqres.in/";

    @Test
    @Tag("regression")
    public void checkFormatImageTest(){

        List<UserData> users = given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .get("api/users?page=2")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Assertions.assertTrue(users.stream().allMatch(x->x.getAvatar().endsWith("image.jpg")));
    }

    @Test
    @Tag("regression")
    public void checkUserDataTest(){

        UserData userData = new UserData
                (2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");

        ActualUserData actualUserData = given()
                .spec(Specifications.requestSpec(URL))
                .body(userData)
                .when()
                .get("api/users/2")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().body().jsonPath().getObject("data", ActualUserData.class);

        Assertions.assertTrue(!EqualsBuilder.reflectionEquals(userData, actualUserData));
    }

    @Test
    @Tag("regression")
    public void userNotFoundTest(){

        given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .get("/api/users/23")
                .then()
                .spec(Specifications.responseSpecUniq(404))
                .log().all();
    }

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void successRegTest(){

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .spec(Specifications.requestSpec(URL))
                .body(user)
                .when()
                .post("api/register")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().as(SuccessReg.class);

        Assertions.assertNotNull(successReg.getId());
        Assertions.assertNotNull(successReg.getToken());

        Assertions.assertEquals(id,successReg.getId());
        Assertions.assertEquals(token,successReg.getToken());
    }

    @Test
    @Tag("regression")
    public void unsuccessRegTest(){

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .spec(Specifications.requestSpec(URL))
                .body(user)
                .when()
                .post("/api/register")
                .then()
                .spec(Specifications.responseSpecError400())
                .log().all()
                .extract().as(UnSuccessReg.class);

        Assertions.assertEquals("Missing password", unSuccessReg.getError());
    }

    @Test
    @Tag("regression")
    public void sortedYearsTest(){

        List<ColorsData> colors = given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .get("api/unknown")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assertions.assertEquals(sortedYears, years);
    }

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void checkActualColorData(){

        ColorsData expected = new ColorsData(2, "fuchsia rose", 2001,  "#C74375", "17-2031");

        ColorsData actual = given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .get("/api/unknown/2")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().body().jsonPath().getObject("data", ColorsData.class);

        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    @Tag("regression")
    public void colorsNotFoundTest(){

        given()
        .spec(Specifications.requestSpec(URL))
                .when()
                .get("api/unknown/23")
                .then()
                .spec(Specifications.responseSpecUniq(404))
                .log().all();
    }

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void userCreatedTest(){

        UserCreateData userCreateData =  new UserCreateData("morpheus", "leader");
        Register.SuccessUserCreate successUserCreate = given()
                .spec(Specifications.requestSpec(URL))
                .body(userCreateData)
                .when()
                .post("api/users")
                .then()
                .spec(Specifications.responseSpecUniq(201))
                .log().all()
                .extract().as(Register.SuccessUserCreate.class);

        Assertions.assertNotNull(successUserCreate.getId(), "id не должен быть null");
        Assertions.assertNotNull(successUserCreate.getCreatedAt(), "CreateAt не должен быть null");
        Assertions.assertNotNull(successUserCreate.getName(), "name не должен быть null");
        Assertions.assertNotNull(successUserCreate.getJob(), "Job не должен быть null");

        Assertions.assertEquals(userCreateData.getName(),successUserCreate.getName());
        Assertions.assertEquals(userCreateData.getJob(),successUserCreate.getJob());
    }

    @Test
    @Tag("regression")
    public void userUpdatedTimeTest(){

        UpdateUserData updateUserData = new UpdateUserData("morpheus", "leader");

        UpdateUserResponce updateUserResponce = given()
                .spec(Specifications.requestSpec(URL))
                .body(updateUserData)
                .when()
                .put("api/users/2")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().as(UpdateUserResponce.class);

        Assertions.assertNotNull(updateUserResponce.getUpdatedAt(), "UpdatedAt не должен быть Null");

        Instant currentTime = Clock.systemUTC().instant();
        Instant responseTime = Instant.parse(updateUserResponce.getUpdatedAt());
        long diffMillis = Math.abs(Duration.between(currentTime, responseTime).toMillis());
        Assertions.assertTrue(diffMillis < 2000);
    }

    @Test
    @Tag("regression")
    public void deleteUserTest(){

        given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .delete("api/users/2")
                .then()
                .spec(Specifications.responseSpecUniq(204))
                .log().all();
    }

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void successLoginTest(){

        String token = "QpwL5tke4Pnpja7X4";

        Login login = new Login( "eve.holt@reqres.in", "cityslicka");

        SuccessLogin successLogin = given()
                .spec(Specifications.requestSpec(URL))
                .body(login)
                .when()
                .post("api/login")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().as(SuccessLogin.class);

        Assertions.assertNotNull(successLogin.getToken(), "token не может быть Null");
        Assertions.assertEquals(token, successLogin.getToken());
    }

    @Test
    @Tag("regression")
    public void unSuccessLoginTest(){

        Login login = new Login( "eve.holt@reqres.in", "");
        UnsuccessLogin unsuccessLogin = given()
                .spec(Specifications.requestSpec(URL))
                .body(login)
                .when()
                .post("api/login")
                .then()
                .spec(Specifications.responseSpecError400())
                .log().all()
                .extract().as(UnsuccessLogin.class);

        Assertions.assertEquals("Missing password", unsuccessLogin.getError());
    }

    @RepeatedTest(2)
    @Tag("regression")
    public void delayedRequestTest(){

        long startTime = System.currentTimeMillis();

        List<UserData> users = given()
        .spec(Specifications.requestSpec(URL))
                .queryParam("delay", 3)
                .when()
                .get("api/users")
                .then()
                .spec(Specifications.responseSpecOk200())
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        Assertions.assertTrue(duration >= 3000, "Задержка меньше ожидаемой 3 секунд");
    }
}