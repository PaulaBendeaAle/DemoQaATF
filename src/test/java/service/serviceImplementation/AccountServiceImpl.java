package service.serviceImplementation;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccount;
import service.apiService.AccountApiService;
import service.interfaceService.AccountInterfaceService;

public class AccountServiceImpl implements AccountInterfaceService {

    //facem o instanta de API Service ca sa putem accesa metodele generale POST, GET, DELETE, PUT etc.
    private AccountApiService accountApiService = new AccountApiService();

    @Override
    public Response createAccount(RequestAccount body) {
        return accountApiService.post(body, "Account/v1/User");
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        return accountApiService.post(body, "Account/v1/GenerateToken");
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        String url = "Account/v1/User/" + userId;
        return accountApiService.get(token, url);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        String url = "Account/v1/User/" + userId;
        return accountApiService.delete(token, url);
    }
}
