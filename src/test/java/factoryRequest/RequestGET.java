package factoryRequest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest {
    @Override
    public Response send(RequestInformation requestInformation) {
        Response response= given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .header(requestInformation.getTypeAuthentication(),requestInformation.getValueAuthentication())
                                .log().all()
                           .when()
                                .get(requestInformation.getUrl());
        response.then().log().all();
        return response;
    }
}
