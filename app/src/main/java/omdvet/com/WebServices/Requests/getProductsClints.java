package omdvet.com.WebServices.Requests;

public class getProductsClints {

    String client_id;

    public getProductsClints(String client_id)
    {
        this.client_id = client_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


}