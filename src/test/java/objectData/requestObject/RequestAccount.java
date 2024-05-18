package objectData.requestObject;

import objectData.RequestPreparation;

import java.util.HashMap;

public class RequestAccount implements RequestPreparation {

    private String userName;
    private String password;

    public RequestAccount(HashMap<String, String> testData) {
        prepareObject(testData);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userName":
                    setUserName(testData.get(key));
                    break;
                case "password":
                    setPassword(testData.get(key));
                    break;
            }

        }
        adjustObjectVariable();
    }

    private void adjustObjectVariable() {
        userName = userName + System.currentTimeMillis();
    }
}
