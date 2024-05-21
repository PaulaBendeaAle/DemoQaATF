package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseTokenSuccess implements ResponseNotNull {

    @JsonProperty("token")
    private String token;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("status")
    private String status;
    @JsonProperty("result")
    private String result;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(token);
        Assert.assertNotNull(expires);
        Assert.assertNotNull(status);
        Assert.assertNotNull(result);
    }
}
