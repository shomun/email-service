package com.monirul.test.emailservice.service.sendgrid.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Personalization {

    @SerializedName("to")
    private List<Email> tos = new ArrayList<>();
    @SerializedName("cc")
    private List<Email> ccs;// = new ArrayList<>();
    @SerializedName("bcc")
    private List<Email> bccs;// = new ArrayList<>();
    private String subject;

    public void addToEmail(Email email){
        this.tos.add(email);
    }

    public void addCCEmail(Email email){
//        if(this.ccs == null){
//            this.ccs = new ArrayList<>();
//        }
        this.ccs.add(email);
    }

    public void addBCCEmail(Email email){
        if(this.bccs == null){
            this.bccs = new ArrayList<>();
        }
        this.bccs.add(email);
    }

}
