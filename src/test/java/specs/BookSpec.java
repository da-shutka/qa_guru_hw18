package specs;

import api.auth.AuthorizationApi;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class BookSpec {

    public static RequestSpecification addBookRequestSpec = with()
            .filter(withCustomTemplates())
            .header("Authorization", "Bearer " + AuthorizationApi.getCookies(AuthorizationApi.TOKEN))
            .contentType(JSON)
            .log().all();

    public static ResponseSpecification addBookSuccessResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();
}
