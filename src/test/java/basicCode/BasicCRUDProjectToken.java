package basicCode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BasicCRUDProjectToken {

    @Test
    public void verifyCRUDProjectUsingToken(){
        //GET Token
        Response response = given()
                                   .auth()
                                   .preemptive()
                                   .basic("jb50@jb50.com","12345")
                                   .log().all()
                            .when()
                                    .get("http://todo.ly/api/authentication/token.json");
        response.then().statusCode(200).log().all();
        String token=response.then().extract().path("TokenString");

        // Creation

        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content","JBProjectJSON");
        bodyProject.put("Icon","1");

        response = given()
                     .header("Token",token)
                     .contentType(ContentType.JSON)
                     .accept(ContentType.JSON)
                     .body(bodyProject.toString())
                     .log().all()
                   .when()
                     .post("http://todo.ly/api/projects.json");

        response.then().statusCode(200).log().all();
        response.then().body("Content", equalTo("JBProjectJSON"));

        int idProject= response.then().extract().path("Id");

        // Update

        bodyProject = new JSONObject();
        bodyProject.put("Content","JBProjectJSONUpdate");
        bodyProject.put("Icon","2");
        System.out.println("****************** UPDATE *********************");
        response=given()
                .header("Token",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyProject.toString())
                .log()
                .all()
                .when()
                .put("http://todo.ly/api/projects/"+idProject+".json");
        response.then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectJSONUpdate"))
                .body("Icon",equalTo(2))
                .log()
                .all();

        // Get
        response= given()
                .header("Token",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .get("http://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectJSONUpdate"))
                .body("Icon",equalTo(2))
                .body("Deleted",equalTo(false))
                .log()
                .all();


        // Delete

        response= given()
                .header("Token",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .delete("http://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectJSONUpdate"))
                .body("Icon",equalTo(2))
                .body("Deleted",equalTo(true))
                .log()
                .all();
    }

}
