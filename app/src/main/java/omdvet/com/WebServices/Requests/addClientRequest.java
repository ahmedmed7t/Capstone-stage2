package omdvet.com.WebServices.Requests;

public class addClientRequest {
    private String apiToken;
    private String name;
    private String phone;
    private String address;

    public addClientRequest(String apiToken, String name, String phone, String address) {
        this.apiToken = apiToken;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
