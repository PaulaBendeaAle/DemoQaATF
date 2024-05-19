package tests;

import actions.AccountActions;
import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

public class CreateAccountTest {

    public String userId;
    public RequestAccount requestAccountBody;
    public String token;
    public AccountActions accountActions;

    @Test
    public void testMethod() {
        System.out.println("Step 1: create new account");
        createAccount();
        System.out.println();

        System.out.println("Step 2: generate new token");
        generateToken();
        System.out.println();

        System.out.println("Step 3: get new Account");
        getSpecificAccount();
        System.out.println();

        System.out.println("Step 4: delete specific Account");
        deleteSpecificAccount();
        System.out.println();

        System.out.println("Step 5: get specific account after delete");
        getSpecificAccount();
        System.out.println();
    }

    public void createAccount() {

        accountActions = new AccountActions(); //instantiaza clasa de actiuni necesare

        //pregateste body-ul in functie de fisierul JSON pe care il am
        PropertyUtility propertyUtility = new PropertyUtility("requestData/CreateAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());

        // layer 3 de account actions care le triggeruieste pe cele din layer 2 si 1 si returneaza tipul de obiect specific
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestAccountBody);
        userId = responseAccountBody.getUserID();

//        //definim clientul, configurarile pentru client
//        RequestSpecification requestSpecification = RestAssured.given(); //defineste ca vrem sa construim un request legat de client cu configurari specifice
//        requestSpecification.baseUri("https://demoqa.com/");
//        requestSpecification.contentType("application/json");
//
//        //definim request-ul
//        PropertyUtility propertyUtility = new PropertyUtility("requestData/CreateAccountData");
//        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
//
////        JSONObject createUserBody = new JSONObject();
////        createUserBody.put("userName", "Paula12" + System.currentTimeMillis());
////        createUserBody.put("password", "Password123#Paula");
//
//        requestSpecification.body(requestAccountBody);
//
//        //interactiune cu response-ul
//        Response response = requestSpecification.post("Account/v1/User");
//        System.out.println(response.getStatusCode());
//        Assert.assertEquals(response.getStatusCode(), 201);
//        System.out.println(response.getStatusLine());
//
//        //validam response body-ul
////        ResponseBody responseBody = response.getBody();
//        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
//        System.out.println(responseAccountBody.getUsername());
//        userId = responseAccountBody.getUserID();
//        Assert.assertEquals(responseAccountBody.getUsername(), requestAccountBody.getUserName());
    }

    public void generateToken() {

        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountBody);
        token = responseTokenSuccess.getToken();

//        RequestSpecification requestSpecification = RestAssured.given();
//        requestSpecification.baseUri("https://demoqa.com/");
//        requestSpecification.contentType("application/json");
//
//        requestSpecification.body(requestAccountBody);
//
//        Response response = requestSpecification.post("Account/v1/GenerateToken");
//        System.out.println(response.getStatusCode());
//        Assert.assertEquals(response.getStatusCode(), 200);
//        System.out.println(response.getStatusLine());
//
//        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
//        System.out.println(responseTokenSuccess.getToken());
//        token = responseTokenSuccess.getToken();
//        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
//        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");
    }

    public void getSpecificAccount() {

        accountActions.getSpecificAccount(token, userId, requestAccountBody);

//        RequestSpecification requestSpecification = RestAssured.given();
//        requestSpecification.baseUri("https://demoqa.com/");
//        requestSpecification.contentType("application/json");
//
//        requestSpecification.header("Authorization", "Bearer " + token);
//
//        Response response = requestSpecification.get("Account/v1/User/" + userId);
//        System.out.println(response.getStatusCode());
//        Assert.assertEquals(response.getStatusCode(), 200);
//        System.out.println(response.getStatusLine());
//
//        ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
//        System.out.println(responseAccountGetSuccess.getUsername());
//        Assert.assertEquals(responseAccountGetSuccess.getUsername(), requestAccountBody.getUserName());
    }

    public void deleteSpecificAccount() {
        accountActions.deleteSpecificAccount(token, userId);
    }

}
