package org.openapitools.openapigeneratorjavawiremockdemo;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.openapigeneratorjavawiremockdemo.wiremock.DefaultApiMockServer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest
class OpenapiGeneratorJavaWiremockDemoApplicationTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void happyPath(WireMockRuntimeInfo wm) {
        String[] args = new String[]{wm.getHttpBaseUrl(), "jug@example.org", "Hello Jug!"};
        var payload = """
        {
            "recipient": "jug@example.org",
            "body": "Hello Jug!"
        }""";
        // Retrieved and stored after a call to the API with the same payload
        var response = """
        {
            "recipient": "jug@example.org",
            "body" : "Hello Jug!",
            "status" : "sent"
        }""";
        stubFor(DefaultApiMockServer.stubPostMail201(payload, response));

        OpenapiGeneratorJavaWiremockDemoApplication.main(args);

        var expected = """
        class Mail {
            status: sent
            recipient: jug@example.org
            body: Hello Jug!
        }""";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}