package com.monirul.test.emailservice;

import com.monirul.test.emailservice.service.EmailService;
import com.monirul.test.emailservice.service.EmailApiServiceProvider;
import com.monirul.test.emailservice.service.http.request.PostHttpCommand;
import com.monirul.test.emailservice.service.mailgun.MailGunApiConfig;
import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import com.monirul.test.emailservice.service.sendgrid.SendGridApiConfig;
import com.monirul.test.emailservice.service.sendgrid.SendGridEmailer;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EmailService emailService(EmailApiServiceProvider emailApiServiceProvider){
        return new EmailService(emailApiServiceProvider);
    }

    @Bean("emailApiServiceProvider")
    public EmailApiServiceProvider emailApiServiceProvider(){
        return new EmailApiServiceProvider();
    }

    @Bean
    public SendGridEmailer sendGridEmailer(PostHttpCommand sendGridHttpRequestCommand){
        return new SendGridEmailer(sendGridHttpRequestCommand);
    }

    @Bean("sendGridHttpRequestCommand")
    public PostHttpCommand sendGridHttpRequestCommand(SendGridApiConfig sendGridApiConfig){
        HttpPost post = new HttpPost(sendGridApiConfig.getUrl());
        post.setHeader(HttpHeaders.AUTHORIZATION, sendGridApiConfig.getAuthorizationKey());
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return new PostHttpCommand(post);
    }

    @Bean
    public SendGridApiConfig sendGridApiConfig(){
        return new SendGridApiConfig();
    }

    @Bean
    public MailGunApiConfig mailGunApiConfig(){
        return new MailGunApiConfig();
    }

    @Bean
    public MailGunEmailer mailGunEmailer(PostHttpCommand mailGunHttpRequestCommand){
        return new MailGunEmailer(mailGunHttpRequestCommand);
    }

    @Bean(name="mailGunHttpRequestCommand")
    public PostHttpCommand mailGunHttpRequestCommand(@Qualifier("mailGunApiConfig") MailGunApiConfig mailGunApiConfig) throws Exception {
        HttpPost post = new HttpPost(mailGunApiConfig.getUrl());
        post.setHeader(HttpHeaders.AUTHORIZATION, mailGunApiConfig.getAuthorizationKey());
     //   post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return new PostHttpCommand(post);
    }


}
