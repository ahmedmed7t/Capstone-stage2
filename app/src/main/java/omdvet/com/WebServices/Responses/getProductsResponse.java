package omdvet.com.WebServices.Responses;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.Orders;

public class getProductsResponse {
    private int status;
    public ArrayList<Orders> orders;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
