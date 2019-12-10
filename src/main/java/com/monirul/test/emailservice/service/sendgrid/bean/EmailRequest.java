package com.monirul.test.emailservice.service.sendgrid.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailRequest {

    private List<Personalization> personalizations = new ArrayList<>();

    @SerializedName("content")
    private List<Content> contents = new ArrayList<>();

    private Email from;

    public void addPersonalization(Personalization personalization){
        this.personalizations.add(personalization);
    }

    public void addContent(Content content){
        this.contents.add(content);
    }

}
