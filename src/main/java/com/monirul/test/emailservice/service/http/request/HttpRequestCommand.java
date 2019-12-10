package com.monirul.test.emailservice.service.http.request;

import org.apache.http.HttpEntity;

import java.io.IOException;

public interface HttpRequestCommand<T,R> {

    R execute(HttpEntity entity) throws IOException;
}
