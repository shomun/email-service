package com.monirul.test.emailservice.service;

public interface EmailApiConfig {

    /**
     * Get Email APIs url
     * @return url
     */
    String getUrl();

    /**
     * Get APIkey
     * @return
     * @throws Exception
     */
    String getAuthorizationKey() throws Exception;
}
