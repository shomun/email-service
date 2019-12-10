package com.monirul.test.emailservice.api;

import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.EmailService;
import com.monirul.test.emailservice.service.http.request.PostHttpCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public String send(@Valid @RequestBody EmailData emailData) throws Exception {
        logger.debug("Received a request to send email to : "+ emailData.getTos());
        emailService.sendEmail(emailData);
        return "Email has been sent successfully";
    }
}
