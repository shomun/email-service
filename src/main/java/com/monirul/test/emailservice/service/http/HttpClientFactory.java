package com.monirul.test.emailservice.service.http;

import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientFactory {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

    public  static CloseableHttpClient getHttpClient(){
        logger.debug("Get a HttpClient ...");
        return HttpClients.custom().setMaxConnTotal(10).build();
    }

}
