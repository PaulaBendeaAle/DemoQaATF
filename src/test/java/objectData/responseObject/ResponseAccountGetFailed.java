package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseAccountGetFailed {

    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
