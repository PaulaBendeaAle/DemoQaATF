package service.serviceImplementation;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccount;
import service.apiService.AccountApiService;
import service.interfaceService.AccountInterfaceService;

public class AccountServiceImpl implements AccountInterfaceService {

    //facem o instanta de API Service ca sa putem accesa metodele generale POST, GET, DELETE, PUT etc.
    private AccountApiService accountApiService;

    @Override
    public Response createAccount(RequestAccount body) {
        accountApiService = new AccountApiService();
        return accountApiService.post(body, "Account/v1/User");
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        accountApiService = new AccountApiService();
        return accountApiService.post(body, "Account/v1/GenerateToken");
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        return null;
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        return null;
    }
}
