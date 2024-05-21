package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import objectData.responseObject.modelObject.ResponseBookObject;

import java.util.List;

@Getter
public class ResponseAccountGetSuccess {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<ResponseBookObject> books;

}
