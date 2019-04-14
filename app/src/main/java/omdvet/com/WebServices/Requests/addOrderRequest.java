package omdvet.com.WebServices.Requests;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.Product;

public class addOrderRequest {
    public String apiToken;
    public String type;
    public Product [] products;
    public int client_id;
    public int emp_id;
    public int is_cach;
    public String date;
    public float cost;
    public float afterDiscount;


    public addOrderRequest(String apiToken, String type, Product[] products, int client_id, int emp_id, int is_cach, String date, float cost, float afterDiscount) {
        this.apiToken = apiToken;
        this.type = type;
        this.products = products;
        this.client_id = client_id;
        this.emp_id = emp_id;
        this.is_cach = is_cach;
        this.date = date;
        this.cost = cost;
        this.afterDiscount = afterDiscount;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getIs_cach() {
        return is_cach;
    }

    public void setIs_cach(int is_cach) {
        this.is_cach = is_cach;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(float afterDiscount) {
        this.afterDiscount = afterDiscount;
    }
}
