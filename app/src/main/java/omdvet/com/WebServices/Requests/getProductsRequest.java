package omdvet.com.WebServices.Requests;

public class getProductsRequest {
    private String apiToken;

    public getProductsRequest(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
