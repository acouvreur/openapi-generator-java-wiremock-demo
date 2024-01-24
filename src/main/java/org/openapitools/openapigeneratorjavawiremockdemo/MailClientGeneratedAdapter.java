package org.openapitools.openapigeneratorjavawiremockdemo;

import org.openapitools.openapigeneratorjavawiremockdemo.api.DefaultApi;
import org.openapitools.openapigeneratorjavawiremockdemo.model.Mail;
import org.openapitools.openapigeneratorjavawiremockdemo.model.NewMail;
import org.springframework.web.reactive.function.client.WebClient;

public class MailClientGeneratedAdapter implements MailClient {

    private final DefaultApi api;

    public MailClientGeneratedAdapter(String host, WebClient webClient) {
        var client = new ApiClient(webClient);
        client.setBasePath(host);
        this.api = new DefaultApi(client);
    }


    @Override
    public Mail sendMail(NewMail mail) {
        return api.postMail(mail).block();
    }
}
