package com.monirul.test.emailservice.service.mailgun;

import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.AbstarctEmailer;
import com.monirul.test.emailservice.service.EmailService;
import com.monirul.test.emailservice.service.http.request.PostHttpCommand;
import com.monirul.test.emailservice.service.http.response.MailGunHttpResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailGunEmailer extends AbstarctEmailer<EmailData> {

    private static final Logger logger = LoggerFactory.getLogger(MailGunEmailer.class);

    public MailGunEmailer(@Qualifier("mailGunHttpRequestCommand") PostHttpCommand httpRequestCommand) {
        super(httpRequestCommand);
        this.responseHandler = new MailGunHttpResponseHandler();
        this.successCode = 200;
    }

    public HttpEntity buildRequest(EmailData email) throws Exception {
        logger.debug("building request object ... ");
        final List<NameValuePair> formFields = new ArrayList<>();
        if(!StringUtils.isEmpty(email.getTos())){
            formFields.add(new BasicNameValuePair("to",  getEmailList(email.getTos())));
        }
        if(!StringUtils.isEmpty(email.getCcs())){
            formFields.add(new BasicNameValuePair("cc",  getEmailList(email.getCcs())));
        }
        if(!StringUtils.isEmpty(email.getBccs())){
            formFields.add(new BasicNameValuePair("bcc",  getEmailList(email.getBccs())));
        }
        formFields.add(new BasicNameValuePair("from",  " <" + email.getSender() + ">"));
        formFields.add(new BasicNameValuePair("subject", email.getSubject()));
        formFields.add(new BasicNameValuePair("text", email.getBody()));
        return new UrlEncodedFormEntity(formFields, "UTF-8");
    }

    private String getEmailList(String emails){
        String[] receivers =  emails.split(",");
        List<String> modifiedEmailList = Arrays.stream(receivers).map(r-> "<"+ r +">").collect(Collectors.toList());
        return String.join(",",modifiedEmailList);

    }


}
