package objectData.requestObject;

import lombok.Data;

import java.util.HashMap;

@Data
public class RequestAccountBook implements RequestPreparation {

    private String userId;
    private String isbn;

    public RequestAccountBook(HashMap<String, String> testData) {
        prepareObject(testData);
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userId":
                    setUserId(testData.get(key));
                    break;
                case "isbn":
                    setIsbn(testData.get(key));
                    break;
            }
        }
    }
}
