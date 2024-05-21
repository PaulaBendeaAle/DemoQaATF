package objectData.requestObject;

import java.util.HashMap;

public interface RequestPreparation {

    //aceasta interfata are ca scop sa serializeze un specific request body
    //toate requesturile o sa implementeze aceasta interfata
    void prepareObject(HashMap<String, String> testData);

}
