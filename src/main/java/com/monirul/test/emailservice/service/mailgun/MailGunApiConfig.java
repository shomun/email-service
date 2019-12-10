package com.monirul.test.emailservice.service.mailgun;

import com.monirul.test.emailservice.service.EmailApiConfig;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class MailGunApiConfig implements EmailApiConfig {
    @Value( "${mailgun.api.url}" )
    private String apiUrl;

    @Value( "${mailgun.api.key}" )
    private String apiKey;

    @Value( "${mailgun.api.userid}" )
    private String userId;

    @Override
    public String getUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorizationKey() throws Exception {
        try {
            return "Basic "+ Base64.getEncoder().encodeToString((userId.concat(":").concat(apiKey)).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new Exception("Unable to encode api key. ", e);
        }
    }
}
