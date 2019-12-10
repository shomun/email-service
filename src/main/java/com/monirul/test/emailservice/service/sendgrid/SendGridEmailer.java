package com.monirul.test.emailservice.service.sendgrid;

import com.google.gson.Gson;
import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.AbstarctEmailer;
import com.monirul.test.emailservice.service.http.request.PostHttpCommand;
import com.monirul.test.emailservice.service.http.response.SendGridHttpResponseHandler;
import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import com.monirul.test.emailservice.service.sendgrid.bean.Content;
import com.monirul.test.emailservice.service.sendgrid.bean.Email;
import com.monirul.test.emailservice.service.sendgrid.bean.EmailRequest;
import com.monirul.test.emailservice.service.sendgrid.bean.Personalization;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SendGridEmailer extends AbstarctEmailer<EmailData> {

    private static final Logger logger = LoggerFactory.getLogger(SendGridEmailer.class);

    public SendGridEmailer(@Qualifier("sendGridHttpRequestCommand") PostHttpCommand httpRequestCommand) {
        super(httpRequestCommand);
        this.responseHandler = new SendGridHttpResponseHandler();
        this.successCode = 202;
    }

    @Override
    public HttpEntity buildRequest(EmailData email) throws Exception {
        logger.debug("building request object ... ");
        EmailRequest request = new EmailRequest();
        Personalization personalization = new Personalization();
        personalization.setTos(getEmailList(email.getTos()));
        personalization.setCcs(getEmailList(email.getCcs()));
        personalization.setBccs(getEmailList(email.getBccs()));
        personalization.setSubject(email.getSubject());

        Email from = new Email();
        from.setEmail(email.getSender());

        request.setFrom(from);
        request.addPersonalization(personalization);
        request.addContent(new Content(email.getBody()));
        Gson gson = new Gson();
        String data = gson.toJson(request);
        return new StringEntity(data);
    }



    private List<Email> getEmailList(String emails){
        List<Email> emailList = null;
        if(!StringUtils.isEmpty(emails)){
            String[] receivers =  emails.split(",");
            emailList = new ArrayList<>();
            List<Email> finalEmailList = emailList;
            Arrays.stream(receivers).forEach(r-> {
                Email reciever = new Email();
                reciever.setEmail(r);
                finalEmailList.add(reciever);
            });
        }

       return emailList;
    }


}
