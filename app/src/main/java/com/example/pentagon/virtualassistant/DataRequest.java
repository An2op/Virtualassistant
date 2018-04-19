package com.example.pentagon.virtualassistant;

public class DataRequest {

   // {"ticket":[{"Id":"1","userid":"2","ticket_type":"Bus","from_to":"EKM - TVM","travel_date":"24-4-2018","status":"Requested","details":"Any Timing","request_date":"2018-04-17 23:32:30"},
public String Id;
    public String ticket_type;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getFrom_to() {
        return from_to;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String from_to;
    public String travel_date;
    public String request_date;
    public String status;
    public String details;

}
