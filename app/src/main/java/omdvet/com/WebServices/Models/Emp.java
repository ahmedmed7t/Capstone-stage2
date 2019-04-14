package omdvet.com.WebServices.Models;

import com.google.gson.annotations.SerializedName;

public class Emp {
    @SerializedName("id")
    public String id;

    public String name;
    public String apiToken;
    public String email;
    public String phone;
    public String is_admin;
    public String Address;
    public String User_id;
    public String gain;
    public String clients;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public String getAddress() {
        return Address;
    }

    public String getUser_id() {
        return User_id;
    }

    public String getGain() {
        return gain;
    }

    public String getClients() {
        return clients;
    }
}
