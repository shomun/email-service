package com.monirul.test.emailservice.service.http.response;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpResponseHandler implements HttpResponseHandler<CloseableHttpResponse, HttpResponse> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHttpResponseHandler.class);
    protected CloseableHttpResponse apiResponse;

    protected HttpResponse response = new HttpResponse();

    @Override
    public HttpResponse handle(CloseableHttpResponse apiResponse,int successCode) throws Exception {
        this.apiResponse = apiResponse;
        if(apiResponse != null && apiResponse.getStatusLine().getStatusCode() == successCode){
            return handleSuccess();
        }else{
            return handleFailure();
        }
    }

    protected HttpResponse handleFailure() throws Exception {
        logger.debug("handling failure ...");
        response.setStatus(HttpResponseStatus.FAILURE);
        response.setStatusCode(apiResponse.getStatusLine().getStatusCode());
        return handleErrorMessage();
    }

    protected HttpResponse handleSuccess(){
        logger.debug("handling success ...");
        response.setStatus(HttpResponseStatus.SUCCESS);
        response.setStatusCode(apiResponse.getStatusLine().getStatusCode());
        return response;
    }

    protected abstract HttpResponse handleErrorMessage() throws Exception;
}
