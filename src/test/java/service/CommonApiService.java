package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restClient.RequestType;
import restClient.RestClient;

public class CommonApiService {

    // Layer 2: presupune definirea actiunilor care se vor face pe configurarile de client (Layer 1)
    // 3-2-1 la layere

    public Response post(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un POST cu un body
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_POST);

        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response get(String token, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_GET);

        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response delete(String token, String endPoint){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_DELETE);

        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    //metoda care instantiaza legatura cu Layer 1
    private Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint) {
        return new RestClient().performRequest(requestType, requestSpecification, endPoint);
    }

}
