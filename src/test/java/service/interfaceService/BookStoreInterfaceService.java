package service.interfaceService;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccountBook;
import objectData.requestObject.RequestAccountBooks;


public interface BookStoreInterfaceService {

    //Aceasta interfata reprezinta actiunile pe care serviciul Bookstore le poate face

    Response addBooksToAccount(RequestAccountBooks body, String token);

    Response updateSpecificBook(RequestAccountBook body, String token, String actualBook);

    Response deleteSpecificBook(RequestAccountBook body, String token);

    Response deleteBooks(String token, String userId);
}
