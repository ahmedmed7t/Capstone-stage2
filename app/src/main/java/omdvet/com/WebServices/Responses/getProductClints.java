package omdvet.com.WebServices.Responses;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.billes;

public class getProductClints {

    int status;
    ArrayList<billes> billes;

    public getProductClints(int status, ArrayList<omdvet.com.WebServices.Models.billes> billes) {
        this.status = status;
        this.billes = billes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<omdvet.com.WebServices.Models.billes> getBilles() {
        return billes;
    }

    public void setBilles(ArrayList<omdvet.com.WebServices.Models.billes> billes) {
        this.billes = billes;
    }

}