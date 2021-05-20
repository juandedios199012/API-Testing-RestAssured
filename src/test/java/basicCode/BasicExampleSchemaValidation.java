package basicCode;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

public class BasicExampleSchemaValidation {
    @Test
    public void createProjectUsingSchemaValidation(){
        JSONObject body = new JSONObject();
        body.put("Content","JBGROUP");
        body.put("Icon","3");

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();


        given()
                .auth()
                .preemptive()
                .basic("jb50@jb50.com","12345")
                .body(body.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("JBGROUP"))
                .body("Icon",equalTo(3))
                .body(matchesJsonSchemaInClasspath("responseSchemaCreateProject.json").using(jsonSchemaFactory))
                .log()
                .all();
    }

}
