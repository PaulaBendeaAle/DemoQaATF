package service.serviceImplementation;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccount;
import service.apiService.AccountApiService;
import service.endpoints.AccountEndpoints;
import service.interfaceService.AccountInterfaceService;

public class AccountServiceImpl implements AccountInterfaceService {

    //facem o instanta de API Service ca sa putem accesa metodele generale POST, GET, DELETE, PUT etc.

    private final AccountApiService accountApiService;

    public AccountServiceImpl() {
        accountApiService = new AccountApiService();
    }

    @Override
    public Response createAccount(RequestAccount body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_CREATE_ENDPOINT);
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_TOKEN_ENDPOINT);
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        String url = AccountEndpoints.ACCOUNT_GET_ENDPOINT + userId;
        return accountApiService.get(token, url);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        String url = AccountEndpoints.ACCOUNT_DELETE_ENDPOINT + userId;
        return accountApiService.delete(token, url);
    }
}
