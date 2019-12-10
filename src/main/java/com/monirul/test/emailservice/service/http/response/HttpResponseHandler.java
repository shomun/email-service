package com.monirul.test.emailservice.service.http.response;

import java.io.IOException;

public interface HttpResponseHandler<T,R> {

    R handle(T apiResponse,int successCode) throws Exception;
}
