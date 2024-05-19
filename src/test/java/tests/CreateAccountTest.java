package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountGetSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.testng.Assert;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

public class CreateAccountTest {

    public String userId;
    public RequestAccount requestAccountBody;
    public String token;

    @Test
    public void testMethod() {
        System.out.println("Step 1: create new account");
        createAccount();
        System.out.println();

        System.out.println("Step 2: generate new token");
        generateToken();
        System.out.println();

        System.out.println("Step 3: Get New Account");
        getSpecificAccount();
        System.out.println();
    }

    public void createAccount() {
        //definim clientul, configurarile pentru client
        RequestSpecification requestSpecification = RestAssured.given(); //defineste ca vrem sa construim un request legat de client cu configurari specifice
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        //definim request-ul
        PropertyUtility propertyUtility = new PropertyUtility("requestData/CreateAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());

//        JSONObject createUserBody = new JSONObject();
//        createUserBody.put("userName", "Paula12" + System.currentTimeMillis());
//        createUserBody.put("password", "Password123#Paula");

        requestSpecification.body(requestAccountBody);

        //interactiune cu response-ul
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getStatusLine());

        //validam response body-ul
//        ResponseBody responseBody = response.getBody();
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println(responseAccountBody.getUsername());
        userId = responseAccountBody.getUserID();
        Assert.assertEquals(responseAccountBody.getUsername(), requestAccountBody.getUserName());
    }

    public void generateToken() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        requestSpecification.body(requestAccountBody);

        Response response = requestSpecification.post("Account/v1/GenerateToken");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getStatusLine());

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        System.out.println(responseTokenSuccess.getToken());
        token = responseTokenSuccess.getToken();
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");
    }

    public void getSpecificAccount() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        requestSpecification.header("Authorization", "Bearer " + token);

        Response response = requestSpecification.get("Account/v1/User/" + userId);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getStatusLine());

        ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
        System.out.println(responseAccountGetSuccess.getUsername());
        Assert.assertEquals(responseAccountGetSuccess.getUsername(), requestAccountBody.getUserName());
    }

}
