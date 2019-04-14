package omdvet.com.WebServices.Responses;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.Clients;

public class clientsResponse {
    private int status;
    private ArrayList<Clients> clients;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Clients> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Clients> clients) {
        this.clients = clients;
    }
}
