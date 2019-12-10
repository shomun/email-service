package com.monirul.test.emailservice;

import com.monirul.test.emailservice.data.EmailData;

public class EmailTestDataUtil {
    public static EmailData createEmailData() {
        EmailData emailData = new EmailData();
        emailData.setTos("shovonz@yahoo.com");
        emailData.setSender("shovonz@yahoo.com");
        emailData.setSubject("New Test SendGrid");
        emailData.setBody("This is a test body");
        return emailData;
    }
}
