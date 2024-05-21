package service.serviceImplementation;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccountBook;
import objectData.requestObject.RequestAccountBooks;
import service.apiService.BookstoreApiService;
import service.endpoints.BookStoreEndpoints;
import service.interfaceService.BookStoreInterfaceService;


public class BookStoreServiceImpl implements BookStoreInterfaceService {


    private final BookstoreApiService bookstoreApiService;

    public BookStoreServiceImpl() {
        bookstoreApiService = new BookstoreApiService();
    }

    @Override
    public Response addBooksToAccount(RequestAccountBooks body, String token) {
        return bookstoreApiService.post(body, BookStoreEndpoints.BOOKSTORE_ADD_ENDPOINT, token);
    }

    @Override
    public Response updateSpecificBook(RequestAccountBook body, String token, String actualBook) {
        String url = BookStoreEndpoints.BOOKSTORE_UPDATE_ENDPOINT + actualBook;
        return bookstoreApiService.put(body, url, token);
    }

    @Override
    public Response deleteSpecificBook(RequestAccountBook body, String token) {
        return bookstoreApiService.delete(body, token, BookStoreEndpoints.BOOKSTORE_DELETE_BOOK_ENDPOINT);
    }

    @Override
    public Response deleteBooks(String token, String userId) {
        String url = BookStoreEndpoints.BOOKSTORE_DELETE_BOOKS_ENDPOINT + userId;
        return bookstoreApiService.delete(token, url);
    }
}
