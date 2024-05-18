package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountSuccess;
import org.testng.Assert;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

public class CreateAccountTest {

    @Test
    public void testMethod(){
        //definim clientul, configurarile pentru client
        RequestSpecification requestSpecification = RestAssured.given(); //defineste ca vrem sa construim un request legat de client cu configurari specifice
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        //definim request-ul
        PropertyUtility propertyUtility = new PropertyUtility("requestData/CreateAccountData");
        RequestAccount requestAccountBody = new RequestAccount(propertyUtility.getAllData());

//        JSONObject createUserBody = new JSONObject();
//        createUserBody.put("userName", "Paula12" + System.currentTimeMillis());
//        createUserBody.put("password", "Password123#Paula");

        requestSpecification.body(requestAccountBody);

        //interactiune cu response-ul
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        //validam response body-ul
//        ResponseBody responseBody = response.getBody();
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println(responseAccountBody.getUsername());
        Assert.assertEquals(responseAccountBody.getUsername(), requestAccountBody.getUserName());
    }

}
