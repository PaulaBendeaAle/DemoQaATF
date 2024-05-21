package actions;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccountBook;
import objectData.requestObject.RequestAccountBooks;
import objectData.responseObject.ResponseAccountBooksSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import org.testng.Assert;
import restClient.ResponseStatus;
import service.serviceImplementation.BookStoreServiceImpl;

public class BookStoreActions {

    private final BookStoreServiceImpl bookStoreServiceImpl;

    public BookStoreActions() {
        bookStoreServiceImpl = new BookStoreServiceImpl();
    }

    public void addBooksToAccount(String token, RequestAccountBooks requestAccountBooks) {

        Response response = bookStoreServiceImpl.addBooksToAccount(requestAccountBooks, token);

        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);
        ResponseAccountBooksSuccess responseAccountBooksBody = response.body().as(ResponseAccountBooksSuccess.class);
        responseAccountBooksBody.validateNotNullFields();
        responseAccountBooksBody.validateBooks(requestAccountBooks.getCollectionsOfIsbns());

    }

    public void updateSpecificBookFromAccount(String token, RequestAccountBook requestBody, String actualBook) {
        Response response = bookStoreServiceImpl.updateSpecificBook(requestBody, token, actualBook);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);
        responseAccountSuccess.validateNotNullFields();
        responseAccountSuccess.validateBookPresence(requestBody.getIsbn());
    }

    public void deleteSpecificBookFromAccount(String token, RequestAccountBook requestBody) {
        Response response = bookStoreServiceImpl.deleteSpecificBook(requestBody, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }

    public void deleteBooksFromAccount(String token, String userId) {
        Response response = bookStoreServiceImpl.deleteBooks(token, userId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }
}
