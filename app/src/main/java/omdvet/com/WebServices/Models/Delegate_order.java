package omdvet.com.WebServices.Models;

/**
 * Created by CrazyNet on 14/04/2019.
 */

public class Delegate_order {

    private String name;
    private String address;
    private String date;
    private String phone;
    private double cost_before;
    private double final_cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getCost_before() {
        return cost_before;
    }

    public void setCost_before(double cost_before) {
        this.cost_before = cost_before;
    }

    public double getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(double final_cost) {
        this.final_cost = final_cost;
    }
}
