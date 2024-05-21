package objectData.requestObject;

import lombok.Data;
import objectData.requestObject.modelObject.RequestBookObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class RequestAccountBooks implements RequestPreparation {

    private String userId;
    private List<RequestBookObject> collectionsOfIsbns;

    public RequestAccountBooks (HashMap<String,String> testData){
        prepareObject(testData);
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userId" -> setUserId(testData.get(key));
                case "collectionOfIsbns" -> prepareBooks(testData.get(key));
            }
        }
    }

    // Trebuie sa parsam valoarea pentru carti intr-o lista de obiecte (BookObject)
    private void prepareBooks(String value){
        collectionsOfIsbns = new ArrayList<>();
        String[] books = value.split(",");

        for (String book : books){
           collectionsOfIsbns.add(new RequestBookObject(book));
        }
    }
}
