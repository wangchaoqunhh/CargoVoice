package com.lwb.cargovoice.module.mvp.entity.request;

public class AIRequest {


    /**
     * sender : apollo
     * message : 发货人是ENO
     */

    private String sender;
    private String message;
    private String title;
    private String payload;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
