package com.codeHub.utils;

import java.util.Date;

public class Metadata {

    private Date timeStamp;
    private String queryTime;
    private String message;

    public Metadata(Date timeStamp, String queryTime, String message) {
        this.timeStamp = timeStamp;
        this.queryTime = queryTime;
        this.message = message;
    }

    public Metadata() {
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "timeStamp=" + timeStamp +
                ", queryTime='" + queryTime + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
