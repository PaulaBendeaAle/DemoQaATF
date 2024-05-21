package actions;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountGetFailed;
import objectData.responseObject.ResponseAccountGetSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.testng.Assert;
import restClient.ResponseStatus;
import service.serviceImplementation.AccountServiceImpl;

public class AccountActions {

    private final AccountServiceImpl accountService;

    public AccountActions() {
        accountService = new AccountServiceImpl();
    }

    public ResponseAccountSuccess createNewAccount(RequestAccount requestAccount) {
        Response response = accountService.createAccount(requestAccount);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        responseAccountBody.validateNotNullFields();
        Assert.assertEquals(responseAccountBody.getUsername(), requestAccount.getUserName());
        return responseAccountBody;
    }

    public ResponseTokenSuccess generateToken(RequestAccount requestAccount) {
        Response response = accountService.generateAccountToken(requestAccount);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        responseTokenSuccess.validateNotNullFields();
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");
        return responseTokenSuccess;
    }

    public void getSpecificAccount(String token, String userId, RequestAccount requestAccountBody) {
        Response response = accountService.getSpecificAccount(token, userId);
        if (response.getStatusCode() == ResponseStatus.SC_OK) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

            ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
            Assert.assertEquals(responseAccountGetSuccess.getUsername(), requestAccountBody.getUserName());
        } else {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_UNAUTHORIZED);

            ResponseAccountGetFailed responseAccountGetFailed = response.body().as(ResponseAccountGetFailed.class);
            Assert.assertEquals(responseAccountGetFailed.getCode(), "1207");
            Assert.assertEquals(responseAccountGetFailed.getMessage(), "User not found!");
        }
    }

    public void deleteSpecificAccount(String token, String userId) {
        Response response = accountService.deleteSpecificAccount(token, userId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }

}
