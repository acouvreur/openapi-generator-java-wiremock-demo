package org.openapitools.openapigeneratorjavawiremockdemo;

import org.openapitools.openapigeneratorjavawiremockdemo.api.DefaultApi;
import org.openapitools.openapigeneratorjavawiremockdemo.model.Mail;
import org.openapitools.openapigeneratorjavawiremockdemo.model.NewMail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.*;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

public class OpenapiGeneratorJavaWiremockDemoApplication {

	public static void main(String[] args) {
		WebClient client = ApiClient.buildWebClientBuilder()
				.build();

		MailClient api = new MailClientAlamano(args[0], client);
		// MailClient api = new MailClientGeneratedAdapter(args[0], client);
		NewMail newMail = new NewMail();
		newMail.setRecipient(args[1]);
		newMail.setBody(args[2]);

		try {
			Mail result = api.sendMail(newMail);
			System.out.println(result);
		} catch (WebClientResponseException e) {
			System.err.println("Exception when calling MailClient#sendMail");
			System.err.println("Status code: " + e.getStatusCode());
			System.err.println("Reason: " + e.getResponseBodyAsString());
			System.err.println("Response headers: " + e.getHeaders());
		}
	}
}
