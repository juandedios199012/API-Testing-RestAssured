package basicCode;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BasicRestAssured {

    @Test
    public void getProject(){
        // given ---> configuracion
        // when ---> method
        // then ---> verificacion respuesta

        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .log()
                .all()
        .when()
                .get("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .log()
                .all();
    }


    @Test
    public void verifyCreateProject(){
        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .log()
                .all()
                .body("{" +
                        "    \"Content\":\"EynarProject\"," +
                        "    \"Icon\":\"4\"" +
                        "}").
        when()
                .post("http://todo.ly/api/projects.json").
        then()
                .statusCode(200)
                .log()
                .all();
    }

}
