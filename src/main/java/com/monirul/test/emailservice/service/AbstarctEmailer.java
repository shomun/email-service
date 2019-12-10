package com.monirul.test.emailservice.service;

import com.monirul.test.emailservice.data.EmailData;
import com.monirul.test.emailservice.service.http.request.HttpRequestCommand;
import com.monirul.test.emailservice.service.http.response.HttpResponse;
import com.monirul.test.emailservice.service.http.response.HttpResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;



public abstract class AbstarctEmailer<T>  implements Emailer<T> {

    protected HttpResponseHandler responseHandler;

    protected HttpRequestCommand httpRequestCommand;

    protected int successCode;

    protected AbstarctEmailer(HttpRequestCommand httpRequestCommand){
        this.httpRequestCommand = httpRequestCommand;
    }


    public HttpResponse send(EmailData email) throws Exception {
        HttpEntity requestEntity = buildRequest(email);
        CloseableHttpResponse apiResponse = (CloseableHttpResponse)httpRequestCommand.execute(requestEntity);
        return (HttpResponse) responseHandler.handle(apiResponse,successCode);
    }

    public abstract HttpEntity buildRequest(EmailData email) throws Exception;


}
