package com.monirul.test.emailservice.service.sendgrid;

import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.http.response.HttpResponse;
import com.monirul.test.emailservice.service.http.request.PostHttpCommand;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SendGridEmailerTest {

    private SendGridEmailer classUnderTest;


   // private SendGridApiConfig apiConfig = Mockito.mock(SendGridApiConfig.class);

    private PostHttpCommand postCommand = Mockito.mock(PostHttpCommand.class);

    @BeforeEach
    void setUp() {
//        Mockito.doReturn("https://api.sendgrid.com/v3/mail/send").when(apiConfig).getUrl();
//        Mockito.doReturn("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").when(apiConfig).getAuthraizationKey();
        classUnderTest = new SendGridEmailer(postCommand);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void send_should_success() {
        EmailData emailData = createEmailData();

        try {
            CloseableHttpResponse responseMock = mock(CloseableHttpResponse.class);

            StatusLine statusLine = mock(StatusLine.class);
            when(responseMock.getStatusLine()).thenReturn(statusLine);
            when(statusLine.getStatusCode()).thenReturn(202);
            when(postCommand.execute(any(StringEntity.class) )).thenReturn(responseMock);

            HttpResponse response = classUnderTest.send(emailData);

            assertEquals(202, response.getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void send_should_fail_on_no_recipient() {
        EmailData emailData = createEmailData();
        emailData.setTos("");
        try {
            CloseableHttpResponse responseMock = mock(CloseableHttpResponse.class);

            StatusLine statusLine = mock(StatusLine.class);
            when(responseMock.getStatusLine()).thenReturn(statusLine);
            when(statusLine.getStatusCode()).thenReturn(400);
            when(postCommand.execute(any(StringEntity.class) )).thenReturn(responseMock);

            HttpResponse response = classUnderTest.send(emailData);

            assertEquals("FAIL", response.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EmailData createEmailData() {
        EmailData emailData = new EmailData();
        emailData.setTos("shovonz@yahoo.com");
        emailData.setSender("shovonz@yahoo.com");
        emailData.setSubject("New Test SendGrid");
        emailData.setBody("This is a test body");
        return emailData;
    }
}