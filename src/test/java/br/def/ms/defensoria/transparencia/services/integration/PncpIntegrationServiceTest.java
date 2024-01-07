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
import br.def.ms.defensoria.transparencia.configuration.PncpApiConstants;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.ContratacaoData;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.UnidadeData;
import okhttp3.Headers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PncpIntegrationServiceTest extends AbstractExternalServiceTest {
    public static final String PNCP_LOGIN = "loginPncp";
    public static final String PNCP_SENHA = "senhaPncp";
    public static final String PNCP_TIMEZONE = "GMT-0";
    public static final String PNCP_CNPJ = "15412257000128";
    public static final String PNCP_UNIT_CODE = "926605";
    private PncpIntegrationService classUnderTest;

    @BeforeEach
    void initMocks() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        PncpAuthProperties pncpAuthProperties = mockPncpAuthProperties();
        classUnderTest = new PncpIntegrationServiceMocked(restTemplate, pncpAuthProperties);
    }

    @Nested
    class PncpApisTest {
        @Test
        void loginMethodTestSuccess() {
            /* Preparation */
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.put(HttpHeaders.AUTHORIZATION, "Bearer teste");

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));

            /* Execution */
            HttpHeaders result = classUnderTest.loginMethod();

            /* Verification */
            assertNotNull(result);
            assertNotNull(result.get(HttpHeaders.AUTHORIZATION));
            assertNotNull(classUnderTest);
            assertValidRequest(1, HttpMethod.POST, PncpApiConstants.PNCP_LOGIN_V1);
        }

        @Test
        void consultUnitWhenTokenIsValidTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer teste");
            httpHeaders.add(HttpHeaders.DATE, getDataAtualFormatadaParaToken());
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createContratacaoDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", httpHeaders);

            /* Execution */
            UnidadeData result = classUnderTest.consultUnitMethod();

            /* Verification */
            assertValidRequest(1, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_UNIT_V1 + "/" + PNCP_UNIT_CODE);
            assertSuccessResponse(result);
        }

        @Test
        void consultUnitWhenTokenIsExpiredTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer teste");
            httpHeaders.add(HttpHeaders.DATE, "Wed, 1 Nov 2023 08:00:00 GMT");

            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createUnidadeDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", httpHeaders);

            /* Execution */
            UnidadeData result = classUnderTest.consultUnitMethod();

            /* Verification */
            assertValidRequest(2, HttpMethod.POST, PncpApiConstants.PNCP_LOGIN_V1);
            assertValidRequest(2, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_UNIT_V1 + "/" + PNCP_UNIT_CODE);
            assertSuccessResponse(result);
        }

        @Test
        void consultContratationWhenTokenIsValidTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer teste");
            httpHeaders.add(HttpHeaders.DATE, getDataAtualFormatadaParaToken());
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createContratacaoDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", httpHeaders);

            /* Execution */
            ContratacaoData result = classUnderTest.consultContratationMethod(2023, 1);

            /* Verification */
            assertValidRequest(1, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_CONTRATATION_V1 + "/2023/1");
            assertSuccessResponse(result);
        }

        @Test
        void consultContratationWhenTokenIsExpiredTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer teste");
            httpHeaders.add(HttpHeaders.DATE, "Wed, 1 Nov 2023 08:00:00 GMT");

            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createContratacaoDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", httpHeaders);

            /* Execution */
            ContratacaoData result = classUnderTest.consultContratationMethod(2023, 1);

            /* Verification */
            assertValidRequest(2, HttpMethod.POST, PncpApiConstants.PNCP_LOGIN_V1);
            assertValidRequest(2, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_CONTRATATION_V1 + "/2023/1");
            assertSuccessResponse(result);
        }

        @Test
        void consultContratationWhenAuthorizationHeaderIsNullTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createContratacaoDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", httpHeaders);

            /* Execution */
            ContratacaoData result = classUnderTest.consultContratationMethod(2023, 1);

            /* Verification */
            assertValidRequest(2, HttpMethod.POST, PncpApiConstants.PNCP_LOGIN_V1);
            assertValidRequest(2, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_CONTRATATION_V1 + "/2023/1");
            assertSuccessResponse(result);
        }

        @Test
        void consultContratationWhenTokenHeaderIsNullTest() {
            /* Preparation */
            HttpHeaders httpHeaders = new HttpHeaders();
            HashMap<String, String> mockHeaders = new HashMap<>(mockPDefaultHeaders());
            mockHeaders.putAll(httpHeaders.toSingleValueMap());

            enqueue200MockSuccessResponse("{}", Headers.of(mockHeaders));
            enqueue200MockSuccessResponse(createContratacaoDataMockResponse(), Headers.of(mockHeaders));

            /* Stubbing */
            ReflectionTestUtils.setField(classUnderTest, "tokenHeader", null);

            /* Execution */
            ContratacaoData result = classUnderTest.consultContratationMethod(2023, 1);

            /* Verification */
            assertValidRequest(2, HttpMethod.POST, PncpApiConstants.PNCP_LOGIN_V1);
            assertValidRequest(2, HttpMethod.GET, PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + PNCP_CNPJ + PncpApiConstants.PNCP_CONSULT_CONTRATATION_V1 + "/2023/1");
            assertSuccessResponse(result);
        }
    }

    private static class PncpIntegrationServiceMocked extends PncpIntegrationService {
        private final RestTemplate restTemplate;

        PncpIntegrationServiceMocked(RestTemplate restTemplate, PncpAuthProperties pncpAuthProperties) {
            super(pncpAuthProperties);
            this.restTemplate = restTemplate;
        }

        protected RestTemplate getRestTemplate() {
            return restTemplate;
        }
    }

    private String getDataAtualFormatadaParaToken() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.getDefault());
        return zonedDateTime.format(formatter);
    }

    private PncpAuthProperties mockPncpAuthProperties() {
        PncpAuthProperties pncpAuthProperties = new PncpAuthProperties();
        pncpAuthProperties.setLogin(PNCP_LOGIN);
        pncpAuthProperties.setSenha(PNCP_SENHA);
        pncpAuthProperties.setCnpj(PNCP_CNPJ);
        pncpAuthProperties.setUnitCode(PNCP_UNIT_CODE);
        pncpAuthProperties.setTimeZone(PNCP_TIMEZONE);
        pncpAuthProperties.setBaseUrl(mockWebServer.url("").toString());
        return pncpAuthProperties;
    }

    private HashMap<String, String> mockPDefaultHeaders() {
        HashMap<String, String> mockHeaders = new HashMap<>();
        mockHeaders.put(HttpHeaders.CONTENT_TYPE, "application/json");
        return mockHeaders;
    }

    private String createUnidadeDataMockResponse() {
        return "{\n" +
                "  \"id\": 1,\n" +
                "  \"orgao\": {\n" +
                "    \"id\": 100,\n" +
                "    \"nomeOrgao\": \"DPGEMS\"\n" +
                "  },\n" +
                "  \"codigoUnidade\": \"DP_STI_123\",\n" +
                "  \"nomeUnidade\": \"STI\",\n" +
                "  \"municipio\": {\n" +
                "    \"id\": 500,\n" +
                "    \"nomeMunicipio\": \"Campo Grande\"\n" +
                "  },\n" +
                "  \"dataInclusao\": \"2023-01-01T00:00:00\",\n" +
                "  \"dataAtualizacao\": \"2023-11-30T12:34:56\"\n" +
                "}";
    }

    private String createContratacaoDataMockResponse() {
        return "{\n" +
                "    \"valorTotalEstimado\": 24480.0,\n" +
                "    \"valorTotalHomologado\": null,\n" +
                "    \"numeroCompra\": \"00001\",\n" +
                "    \"orgaoSubRogado\": null,\n" +
                "    \"unidadeOrgao\": {\n" +
                "        \"ufNome\": \"Mato Grosso do Sul\",\n" +
                "        \"codigoUnidade\": \"989073\",\n" +
                "        \"nomeUnidade\": \"PREFEITURA MUNICIPAL DE DOURADOS\",\n" +
                "        \"ufSigla\": \"MS\",\n" +
                "        \"municipioNome\": \"Dourados\",\n" +
                "        \"codigoIbge\": \"5003702\"\n" +
                "    },\n" +
                "    \"unidadeSubRogada\": null,\n" +
                "    \"dataAtualizacao\": \"2023-01-05T09:57:41\",\n" +
                "    \"dataInclusao\": \"2023-01-05T09:57:41\",\n" +
                "    \"dataPublicacaoPncp\": \"2023-01-05T09:57:41\",\n" +
                "    \"modalidadeId\": 8,\n" +
                "    \"srp\": false,\n" +
                "    \"anoCompra\": 2023,\n" +
                "    \"sequencialCompra\": 1,\n" +
                "    \"orgaoEntidade\": {\n" +
                "        \"cnpj\": \"15412257000128\",\n" +
                "        \"razaoSocial\": \"ESTADO DE MATO GROSSO DO SUL\",\n" +
                "        \"poderId\": \"N\",\n" +
                "        \"esferaId\": \"E\",\n" +
                "        \"id\": 0,\n" +
                "        \"cnpjEnteResponsavel\": null,\n" +
                "        \"validado\": false,\n" +
                "        \"dataValidacao\": null\n" +
                "    },\n" +
                "    \"amparoLegal\": {\n" +
                "        \"codigo\": 19,\n" +
                "        \"nome\": \"Lei 14.133/2021, Art. 75, II\",\n" +
                "        \"descricao\": \"Dispensa de Licitação: para contratação que envolva valores inferiores a R$ 50.000,00 (cinquenta mil reais), no caso de outros serviços e compras\"\n" +
                "    },\n" +
                "    \"dataAberturaProposta\": \"2023-01-05T09:57:40\",\n" +
                "    \"dataEncerramentoProposta\": \"2023-01-10T07:59:59\",\n" +
                "    \"informacaoComplementar\": \"Art. 75º, Inciso II da Lei nº 14.133 de 1º/04/2021.\",\n" +
                "    \"processo\": \"001\",\n" +
                "    \"objetoCompra\": \"Aquisição de eletrodo descartável, objetivando atender as necessidades da Secretaria Municipal de Saúde.\",\n" +
                "    \"linkSistemaOrigem\": \"https://treinamento.comprasnet.gov.br/acesso.asp?url=/proposta-989073-06-00001-2023\",\n" +
                "    \"justificativaPresencial\": null,\n" +
                "    \"existeResultado\": false,\n" +
                "    \"numeroControlePNCP\": \"15412257000128-1-000001/2023\",\n" +
                "    \"orcamentoSigilosoCodigo\": 1,\n" +
                "    \"orcamentoSigilosoDescricao\": \"Compra sem sigilo\",\n" +
                "    \"situacaoCompraId\": 1,\n" +
                "    \"situacaoCompraNome\": \"Divulgada no PNCP\",\n" +
                "    \"tipoInstrumentoConvocatorioCodigo\": 2,\n" +
                "    \"tipoInstrumentoConvocatorioNome\": \"Aviso de Contratação Direta\",\n" +
                "    \"modoDisputaId\": 4,\n" +
                "    \"modoDisputaNome\": \"Dispensa Com Disputa\",\n" +
                "    \"usuarioNome\": \"siasgnet\",\n" +
                "    \"modalidadeNome\": \"Dispensa de Licitação\"\n" +
                "}";
    }
}
