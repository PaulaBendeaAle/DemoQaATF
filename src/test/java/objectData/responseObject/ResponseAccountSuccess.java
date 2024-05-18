package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import objectData.BookObject;

import java.util.List;

public class ResponseAccountSuccess {

    @JsonProperty("userID")
    private String userID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<BookObject> books;

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<BookObject> getBooks() {
        return books;
    }
}
