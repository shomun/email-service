package com.monirul.test.emailservice.service.sendgrid;

import com.monirul.test.emailservice.ApplicationConfig;
import com.monirul.test.emailservice.EmailTestDataUtil;
import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.http.response.HttpResponse;
import com.monirul.test.emailservice.service.http.response.HttpResponseStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class,SendGridApiConfig.class})
@TestPropertySource(locations="classpath:application.properties")
class SendGridEmailerIntegrationTest {

    @Autowired
    @Qualifier("sendGridEmailer")
    private SendGridEmailer classUnderTest;

    @Test
    void send_should_success() throws Exception {
        EmailData emailData = EmailTestDataUtil.createEmailData();
        HttpResponse response = classUnderTest.send(emailData);
        assertEquals(202, response.getStatusCode());

    }

    @Test
    void send_should_success_for_multiple_recipient() throws Exception {
        EmailData emailData = EmailTestDataUtil.createEmailData();
        emailData.setTos("shovonz@yahoo.com,shomuns@gmail.com");
        HttpResponse response = classUnderTest.send(emailData);
        assertEquals(202, response.getStatusCode());

    }

    @Test
    void send_should_success_for_CC_recipient() throws Exception {
        EmailData emailData = EmailTestDataUtil.createEmailData();
        emailData.setCcs("monirul.mi2560@gmail.com,shomuns@gmail.com");
        HttpResponse response = classUnderTest.send(emailData);
        assertEquals(202, response.getStatusCode());

    }
    @Test
    void send_should_fail_for_duplicate_recipient_in_to_and_cc() throws Exception {
        EmailData emailData = EmailTestDataUtil.createEmailData();
        emailData.setCcs("shovonz@yahoo.com,shomuns@gmail.com");
        HttpResponse response = classUnderTest.send(emailData);
        assertEquals(HttpResponseStatus.FAILURE, response.getStatus());

    }

    @Test
    void send_should_fail_on_no_recipient() throws Exception {
        EmailData emailData = EmailTestDataUtil.createEmailData();
        emailData.setTos("");

        HttpResponse response = classUnderTest.send(emailData);

        assertEquals(HttpResponseStatus.FAILURE, response.getStatus());

    }



}