package api;

import api.pogo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String URL = "https://reqres.in/";

    @Test
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
    public void deleteUserTest(){
        given()
                .spec(Specifications.requestSpec(URL))
                .when()
                .delete("api/users/2")
                .then()
                .spec(Specifications.responseSpecUniq(204))
                .log().all();
    }


}
