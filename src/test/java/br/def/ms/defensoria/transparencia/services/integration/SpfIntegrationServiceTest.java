/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.services.integration;

import br.def.ms.defensoria.transparencia.AbstractExternalServiceTest;
import br.def.ms.defensoria.transparencia.configuration.SpfApiConstants;
import br.def.ms.defensoria.transparencia.models.integration.dto.spf.LoginData;
import okhttp3.Headers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SpfIntegrationServiceTest extends AbstractExternalServiceTest {
    public static final String SPF_USER = "loginSpf";
    public static final String SPF_SENHA = "senhaSpf";
    private SpfIntegrationService classUnderTest;

    @BeforeEach
    void initMocks() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        SpfAuthProperties spfAuthProperties = mockSpfAuthProperties();
        classUnderTest = new SpfIntegrationServiceTest.SpfIntegrationServiceMocked(restTemplate, spfAuthProperties);
    }

    @Nested
    class SpfApisTest {
        @Test
        void loginMethodTestSuccess() {
            /* Preparation */
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.put(HttpHeaders.EXPIRES, "-1");

            enqueue200MockSuccessResponse(loginDataMockResponse(), Headers.of(mockHeaders));

            /* Execution */
            LoginData result = classUnderTest.loginMethod();

            /* Verification */
            assertNotNull(result);
            assertNotNull(result.getData());
            assertNotNull(classUnderTest);
            assertValidRequest(1, HttpMethod.POST, SpfApiConstants.SPF_LOGIN_V1);
        }
    }

    private static class SpfIntegrationServiceMocked extends SpfIntegrationService {
        private final RestTemplate restTemplate;

        SpfIntegrationServiceMocked(RestTemplate restTemplate, SpfAuthProperties spfAuthProperties) {
            super(spfAuthProperties);
            this.restTemplate = restTemplate;
        }

        protected RestTemplate getRestTemplate() {
            return restTemplate;
        }
    }

    private SpfAuthProperties mockSpfAuthProperties() {
        SpfAuthProperties spfAuthProperties = new SpfAuthProperties();
        spfAuthProperties.setUsuario(SPF_USER);
        spfAuthProperties.setSenha(SPF_SENHA);
        spfAuthProperties.setBaseUrl(mockWebServer.url("").toString());
        return spfAuthProperties;
    }

    private HashMap<String, String> mockPDefaultHeaders() {
        HashMap<String, String> mockHeaders = new HashMap<>();
        mockHeaders.put(HttpHeaders.CONTENT_TYPE, "application/json");
        return mockHeaders;
    }

    private String loginDataMockResponse() {
        return "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"token\": \"ede8842d5b4c2e1f8429e4b181c516d9cf817e35bb457fcee37bc68a5ad9b75f10b198f6a6ac7f1bb07214d3e1022dfc7f90827c5453277a1aa500e125a0b4c3\",\n" +
                "            \"usuario\": \"usr_dpge_ms\",\n" +
                "            \"dataExpiracao\": \"2023-12-15T12:52:18.3611453Z\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"message\": \"OK\"\n" +
                "    },\n" +
                "    \"pagination\": {\n" +
                "        \"has_prev\": null,\n" +
                "        \"has_next\": null,\n" +
                "        \"page_count\": 0,\n" +
                "        \"total\": 0\n" +
                "    },\n" +
                "    \"version\": \"1.0\"\n" +
                "}";
    }
}
