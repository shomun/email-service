package com.monirul.test.emailservice.service;


import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.http.response.HttpResponse;
import com.monirul.test.emailservice.service.http.response.HttpResponseStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private EmailApiServiceProvider emailProvider;
    public EmailService(EmailApiServiceProvider emailProvider){
        this.emailProvider = emailProvider;
    }

    @HystrixCommand(fallbackMethod = "sendEmailOnFailover",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "1"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
            }
    )
    public boolean sendEmail(EmailData email) throws Exception {
        logger.debug("sending email using MailGun Email API");
        try{
            HttpResponse response = emailProvider.getDefaultEmailer().send(email);
            if(response.getStatus() == HttpResponseStatus.FAILURE){
                throw new Exception(response.getStatusMessage());
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        logger.info("Successfully sent email using MailGun API");
        return true;
    }

    public boolean sendEmailOnFailover(EmailData email) throws Exception {
        logger.info("Unable send email using deafult email Service. Now using fallback( Send Grid ) email API");
        HttpResponse response = emailProvider.getFallbackEmailer().send(email);
        if(response.getStatus() == HttpResponseStatus.FAILURE){
            throw new Exception(response.getStatusMessage());
        }
        logger.info("Successfully sent email using SendGrid API");
        return true;
    }
}
