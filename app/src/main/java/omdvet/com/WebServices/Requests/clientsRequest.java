package omdvet.com.WebServices.Requests;

public class clientsRequest {
    private  String apiToken;

    public clientsRequest(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
