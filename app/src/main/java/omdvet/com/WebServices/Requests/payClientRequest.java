package omdvet.com.WebServices.Requests;

import com.google.gson.annotations.SerializedName;

public class payClientRequest {
    @SerializedName("client_id")
    public int client_id;

    @SerializedName("mony")
    public double mony;

    public payClientRequest(int client_id, double mony) {
        this.client_id = client_id;
        this.mony = mony;
    }
}
