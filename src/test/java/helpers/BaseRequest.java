package helpers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
    protected final RequestSpecification request;

    public BaseRequest() {
        request = RestAssured.given();
    }
}
