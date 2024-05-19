package service.interfaceService;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccount;

public interface AccountInterfaceService {

    // aceasta interfata reprezinta actiunile pe care vrem sa le facem cu un modul (Accounnt)

    Response createAccount(RequestAccount body);
    Response generateAccountToken(RequestAccount body);
    Response getSpecificAccount(String token, String userId);
    Response deleteSpecificAccount(String token, String userId);

}
