package com.monirul.test.emailservice.service;

import com.monirul.test.emailservice.service.http.response.HttpResponse;

/**
 * Interface to implement Third party email service api adapter
 * @param <T>
 */
public interface Emailer<T> {

    /**
     * send email content to  Email API service
     * @param email
     * @return
     * @throws Exception
     */
    HttpResponse send(T email) throws Exception;
}
