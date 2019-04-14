package omdvet.com.WebServices.Models;

public class Clients {
    
    public  int id;
    public String apiToken;
    public int emp_id;
    public String name;
    public String phone;
    public String address;
    public int is_report;
    public String pay;

    public int getId() {
        return id;
    }

    public String getApiToken() {
        return apiToken;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getIs_report() {
        return is_report;
    }

    public String getPay() {
        return pay;
    }
}
