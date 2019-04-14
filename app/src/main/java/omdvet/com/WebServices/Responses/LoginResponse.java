package omdvet.com.WebServices.Responses;

import omdvet.com.WebServices.Models.Emp;

public class LoginResponse {
       private int status;
       private Emp emp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}
