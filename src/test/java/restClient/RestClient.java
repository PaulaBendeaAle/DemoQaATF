package restClient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import xmlFile.GenerateXml;
import xmlFile.xmlNode.Configuration;

public class RestClient {

    // Layer 1 = clasa unde sunt definite configurari la nivel de client
    // Am de facut 2 actiuni:
    // 1. Metoda care configureaza clientul
    // 2. Metoda care returneaza un raspuns pe baza configurarilor de la client

    private RequestSpecification prepareClient(RequestSpecification requestSpecification) {
//        Configuration configuration = GenerateXml.createConfig(Configuration.class);

//        requestSpecification.baseUri(configuration.backendConfig.baseURL);
//        requestSpecification.contentType(configuration.backendConfig.contentType);
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        return requestSpecification;
    }

    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint) {
        return switch (requestType) {
            case RequestType.REQUEST_POST -> prepareClient(requestSpecification).post(endPoint);
            case RequestType.REQUEST_PUT -> prepareClient(requestSpecification).put(endPoint);
            case RequestType.REQUEST_GET -> prepareClient(requestSpecification).get(endPoint);
            case RequestType.REQUEST_DELETE -> prepareClient(requestSpecification).delete(endPoint);
            default -> null;
        };
    }
}
