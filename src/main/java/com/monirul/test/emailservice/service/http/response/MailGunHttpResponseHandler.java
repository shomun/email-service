package com.monirul.test.emailservice.service.http.response;

import com.google.gson.Gson;
import com.monirul.test.emailservice.service.mailgun.MailGunEmailer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailGunHttpResponseHandler extends AbstractHttpResponseHandler {

    @Override
    protected HttpResponse handleErrorMessage() throws Exception {
        Map<String,String> error = new Gson().fromJson(EntityUtils.toString(apiResponse.getEntity()), HashMap.class);
        response.setStatusMessage(error.get("message"));
        return response;
    }


}
