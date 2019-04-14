package omdvet.com.WebServices.Requests;

import android.support.annotation.Nullable;

public class addProductRequest {

    private String name;
    private String price;
    private String quantity;
    private String photo;
    public addProductRequest(String name, String price, String quantity,@Nullable String photo) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.photo=photo;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
