package com.monirul.test.emailservice.service.http.request;

import com.monirul.test.emailservice.service.http.HttpClientFactory;
import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PostHttpCommand  implements HttpRequestCommand<HttpPost, Object>{

    private static final Logger logger = LoggerFactory.getLogger(PostHttpCommand.class);

    private HttpPost postRequest;

    public PostHttpCommand(HttpPost postRequest){
        this.postRequest = postRequest;
    }

    @Override
    public Object execute(HttpEntity entity) throws IOException {
        CloseableHttpClient httpClient = HttpClientFactory.getHttpClient();
        postRequest.setEntity(entity);
        logger.debug("Executing HTTP Post request");
        return httpClient.execute(postRequest);
    }
}
