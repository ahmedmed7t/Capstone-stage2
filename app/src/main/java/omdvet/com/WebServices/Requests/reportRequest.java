package omdvet.com.WebServices.Requests;

public class reportRequest {
    public int client_id;
    public int is_report;

    public reportRequest(Integer client_id,Integer is_report) {
        this.client_id=client_id;
        this.is_report=is_report;
    }
}
