package omdvet.com.WebServices.Responses;

import com.google.gson.annotations.SerializedName;

import omdvet.com.WebServices.Models.Mony;

public class payClientResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("mony")
    public Mony mony;
}
