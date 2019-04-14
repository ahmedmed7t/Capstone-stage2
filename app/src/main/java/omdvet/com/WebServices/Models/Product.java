package omdvet.com.WebServices.Models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("id")
    private int id;

    public Product(int quantity, int id) {
        this.quantity = quantity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return
                "ProductItem{" +
                        "quantity = '" + quantity + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
