package omdvet.com.WebServices.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.AllBilles;

/**
 * Created by CrazyNet on 18/03/2019.
 */

public class GetAllBillesResponse {

    int status;

    @SerializedName("billes")
    ArrayList<AllBilles> allBilles;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<AllBilles> getAllBilles() {
        return allBilles;
    }

    public void setAllBilles(ArrayList<AllBilles> allBilles) {
        this.allBilles = allBilles;
    }
}
