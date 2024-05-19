package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import objectData.BookObject;

import java.util.List;

public class ResponseAccountGetSuccess {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<BookObject> books;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<BookObject> getBooks() {
        return books;
    }

}
