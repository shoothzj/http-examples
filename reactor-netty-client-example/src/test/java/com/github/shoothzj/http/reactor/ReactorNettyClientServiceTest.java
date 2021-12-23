package com.github.shoothzj.http.reactor;

import com.github.shoothzj.http.common.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

class ReactorNettyClientServiceTest {

    private final ReactorNettyClientService clientService = new ReactorNettyClientService();

    @Test
    void testSyncGet() {
        ClientAndServer clientAndServer = ClientAndServer.startClientAndServer(0);
        String testData = "test data";
        String testPath = "/test";
        new MockServerClient("127.0.0.1", clientAndServer.getLocalPort())
                .when(HttpRequest.request().withMethod("GET").withPath(testPath))
                .respond(org.mockserver.model.HttpResponse.response()
                        .withStatusCode(200)
                        .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                        .withBody(testData)
                        .withDelay(TimeUnit.SECONDS, 1)
                );
        Optional<HttpResponse> httpResponse = clientService.syncGet("localhost",
                clientAndServer.getLocalPort(), testPath);
        Assertions.assertTrue(httpResponse.isPresent());
        Assertions.assertEquals(testData, httpResponse.get().getResp());
    }
}