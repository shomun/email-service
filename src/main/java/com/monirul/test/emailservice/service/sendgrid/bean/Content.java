package com.monirul.test.emailservice.service.sendgrid.bean;

import lombok.Data;

@Data
public class Content {
    private String type = "text/plain";
    private String  value;

    public Content(String value){
        this.value = value;
    }
}
