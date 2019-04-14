package omdvet.com.WebServices.Models;

/**
 * Created by CrazyNet on 18/03/2019.
 */

public class AllBilles {

    int id;
    String name;
    String phone;
    String address;
    String cost;
    String afterDiscount;
    String date;
    String created_at;
    String type;
    String is_cach;
    String mony_agel;
    String emp_id;
    String client_id;

    public AllBilles(int id, String name, String phone, String address, String cost,
                     String afterDiscount, String date, String created_at, String type,
                     String is_cach, String mony_agel, String emp_id, String client_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cost = cost;
        this.afterDiscount = afterDiscount;
        this.date = date;
        this.created_at = created_at;
        this.type = type;
        this.is_cach = is_cach;
        this.mony_agel = mony_agel;
        this.emp_id = emp_id;
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(String afterDiscount) {
        this.afterDiscount = afterDiscount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIs_cach() {
        return is_cach;
    }

    public void setIs_cach(String is_cach) {
        this.is_cach = is_cach;
    }

    public String getMony_agel() {
        return mony_agel;
    }

    public void setMony_agel(String mony_agel) {
        this.mony_agel = mony_agel;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
