package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseAccountGetFailed {

    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

}
