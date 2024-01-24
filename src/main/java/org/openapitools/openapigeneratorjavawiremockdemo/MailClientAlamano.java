package org.openapitools.openapigeneratorjavawiremockdemo;

import org.openapitools.openapigeneratorjavawiremockdemo.model.Mail;
import org.openapitools.openapigeneratorjavawiremockdemo.model.NewMail;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

public class MailClientAlamano implements MailClient {

    private final String host;
    private final WebClient client;

    public MailClientAlamano(String host, WebClient client) {
        this.host = host;
        this.client = client;
    }

    @Override
    public Mail sendMail(NewMail mail) {
        return client.post()
            .uri(URI.create(host + "/mails"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .bodyValue(mail)
            .retrieve()
            .bodyToMono(Mail.class)
            .block();
    }
}
