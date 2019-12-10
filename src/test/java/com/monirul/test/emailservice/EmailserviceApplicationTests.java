package com.monirul.test.emailservice;

import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailserviceApplicationTests {

    @Autowired
    private EmailService classUnderTest;

    @Test
    void send_success() throws Exception {
        EmailData emailData =EmailTestDataUtil.createEmailData();

        boolean success = classUnderTest.sendEmail(emailData);

        assertEquals(true,success);

    }

}
