package com.monirul.test.emailservice.service.http.response;

import lombok.Data;

@Data
public class HttpResponse {
    private HttpResponseStatus status;

    private int statusCode;

    private String statusMessage;
}
