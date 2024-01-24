package org.openapitools.openapigeneratorjavawiremockdemo;

import org.openapitools.openapigeneratorjavawiremockdemo.model.Mail;
import org.openapitools.openapigeneratorjavawiremockdemo.model.NewMail;

public interface MailClient {

    Mail sendMail(NewMail mail);

}
