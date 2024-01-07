/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia;

import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.QueueDispatcher;
import okhttp3.mockwebserver.RecordedRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractExternalServiceTest {
    public static MockWebServer mockWebServer;
    public static WebClient webClient;

    public AbstractExternalServiceTest() {
        // Server
        WebClient.Builder webClientBuilder = WebClient.builder();

        mockWebServer = new MockWebServer();
        ((QueueDispatcher) mockWebServer.getDispatcher()).setFailFast(true);
        webClient = webClientBuilder.baseUrl(mockWebServer.url("").toString()).build();
    }

    public void enqueueMockResponse(String body, Headers mockHeaders) {
        mockWebServer.enqueue(new MockResponse()
                .setHeaders(mockHeaders)
                .setBody(body));
    }

    public void enqueue200MockSuccessResponse(String body, Headers mockHeaders) {
        enqueueMockResponse(body, mockHeaders);
    }

    public void assertValidRequest(int count, HttpMethod method, String expected) {
        assertEquals(count, mockWebServer.getRequestCount());
        final RecordedRequest recordedRequest = assertDoesNotThrow(() -> mockWebServer.takeRequest());
        assertEquals(method.toString(), recordedRequest.getMethod());
        assertEquals(expected, recordedRequest.getPath());
    }

    public void assertSuccessResponse(Object output) {
        assertNotNull(output);
    }
}
