package basicCode;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BasicCRUDTask {

    @Test
    public void verifyCRUDTask(){

        //Create Project
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content","JBProjectJSON-Taller2");
        bodyProject.put("Icon","1");

        Response responseProject=given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .body(bodyProject.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json");
        responseProject.then()
                .statusCode(200)
                .body("Content",equalTo("JBProjectJSON-Taller2"))
                .body("Icon",equalTo(1))
                .log()
                .all();

        String idProject = responseProject.then().extract().path("Id")+"";
        System.out.println("**** ID Project: "+idProject);

        // Create Task

        JSONObject bodyTask = new JSONObject();
        bodyTask.put("Content","JBTaskJSON-Taller2");
        bodyTask.put("ProjectId",idProject);

        Response responseTask=given()
                                .auth()
                                .preemptive()
                                .basic("jb50@jb50.com","12345")
                                .body(bodyTask.toString())
                                .log()
                                .all()
                        .when()
                                .post("https://todo.ly/api/items.json");
        responseTask.then()
                .statusCode(200)
                .body("Content",equalTo("JBTaskJSON-Taller2"))
                //.body("ProjectId",equalTo(idProject))
                .log()
                .all();

        String idTask = responseTask.then().extract().path("Id")+"";
        System.out.println("**** ID Task: "+idTask);

        // Update Task

        bodyTask = new JSONObject();
        bodyTask.put("Content","JBTaskJSONUpdate-Taller2");
        //bodyTask.put("Icon","2");
        System.out.println("****************** UPDATE *********************");
        responseTask=given()
                    .auth()
                    .preemptive()
                    .basic("jb50@jb50.com","12345")
                    .body(bodyTask.toString())
                    .log()
                    .all()
                .when()
                    .put("https://todo.ly/api/items/"+idTask+".json");
        responseTask.then()
                .statusCode(200)
                .body("Content",equalTo("JBTaskJSONUpdate-Taller2"))
                //.body("Icon",equalTo(2))
                .log()
                .all();

        // Get Task
        /** responseTask= given()
                        .auth()
                        .preemptive()
                        .basic("jb50@jb50.com","12345")
                        .log()
                        .all()
                  .when()
                       .get("https://todo.ly/api/items/"+idTask+".json");

        responseTask.then()
                .statusCode(200)
                .body("Content",equalTo("JBTaskJSONUpdate-Taller2"))
                //.body("Icon",equalTo(2))
                .body("Deleted",equalTo(false))
                .log()
                .all();**/


        // Delete Task

        responseTask= given()
                    .auth()
                    .preemptive()
                    .basic("jb50@jb50.com","12345")
                    .log()
                    .all()
                    .when()
                .delete("https://todo.ly/api/items/"+idTask+".json");

        responseTask.then()
                    .statusCode(200)
                    .body("Content",equalTo("JBTaskJSONUpdate-Taller2"))
                    //.body("Icon",equalTo(2))
                    .body("Deleted",equalTo(true))
                    .log()
                    .all();
    }

}
