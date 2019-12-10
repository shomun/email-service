package com.monirul.test.emailservice.service;

import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import com.monirul.test.emailservice.service.sendgrid.SendGridEmailer;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailApiServiceProvider {
    @Autowired
    private SendGridEmailer sendGridEmailer;

    @Autowired
    private MailGunEmailer mailGunEmailer;

    public Emailer getDefaultEmailer(){
        return mailGunEmailer;
    }

    public Emailer getFallbackEmailer(){
        return sendGridEmailer;
    }

}
