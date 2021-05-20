package basicCode;

import org.json.JSONObject;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;


public class BasicCreateProject {
    @Test
    public void createProjectUsingStringBody(){
        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .body("{" +
                        "    \"Content\":\"EynarProject\"," +
                        "    \"Icon\":\"4\"" +
                        "}")
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("EynarProject"))
                .body("Icon",equalTo(4))
                .log()
                .all();
    }

    @Test
    public void createProjectUsingExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .body(new File("C:\\Users\\Eynar\\Desktop\\JBAPIAbril\\jb_api\\src\\test\\resources\\createProjectBody.json"))
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectByFile"))
                .body("Icon",equalTo(2))
                .log()
                .all();
    }

    @Test
    public void createProjectUsingJSONObject() {

        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content","JBProjectJSON");
        bodyProject.put("Icon","1");


        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .body(bodyProject.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectJSON"))
                .body("Icon",equalTo(1))
                .log()
                .all();
    }

}
