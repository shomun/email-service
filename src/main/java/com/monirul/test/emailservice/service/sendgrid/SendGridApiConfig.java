package com.monirul.test.emailservice.service.sendgrid;

import com.monirul.test.emailservice.service.EmailApiConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SendGridApiConfig implements EmailApiConfig {

    @Value( "${sendgrid.api.url}" )
    private String apiUrl;

    @Value( "${sendgrid.api.key}" )
    private String apiKey;

    @Override
    public String getUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorizationKey() {
        return "Bearer "+apiKey;
    }
}
