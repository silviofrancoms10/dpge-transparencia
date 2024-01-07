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

import br.def.ms.defensoria.transparencia.configuration.PncpApiConstants;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.ContratacaoData;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.UnidadeData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PncpIntegrationService {
    private final PncpAuthProperties pncpAuthProperties;
    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders tokenHeader;
    private HttpEntity<PncpAuthProperties> requestEntity;

    public PncpIntegrationService(PncpAuthProperties pncpAuthProperties) {
        this.pncpAuthProperties = pncpAuthProperties;
    }

    public HttpHeaders loginMethod() {
        requestEntity = new HttpEntity<>(pncpAuthProperties);
        ResponseEntity<PncpAuthProperties> responseEntity = restTemplate.postForEntity(getLoginUrl(), requestEntity, PncpAuthProperties.class);
        tokenHeader = responseEntity.getHeaders();
        return tokenHeader;
    }

    public UnidadeData consultUnitMethod() {
        validateToken();
        requestEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<UnidadeData> responseEntity = restTemplate.getForEntity(getConsultUnitUrl(), UnidadeData.class, requestEntity);
        return responseEntity.getBody();
    }

    public ContratacaoData consultContratationMethod(Integer ano, Integer sequencial) {
        validateToken();
        requestEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<ContratacaoData> responseEntity = restTemplate.getForEntity(getConsultContratationUrl(ano, sequencial), ContratacaoData.class, requestEntity);
        return responseEntity.getBody();
    }

    public void validateToken() {
        if (tokenHeader == null || tokenHeader.get(HttpHeaders.AUTHORIZATION) == null || tokenExpired()) {
            loginMethod();
        }
    }

    private boolean tokenExpired() {
        ZonedDateTime tokenExpirationDate = ZonedDateTime.parse(Objects.requireNonNull(tokenHeader.get(HttpHeaders.DATE)).get(0), DateTimeFormatter.RFC_1123_DATE_TIME).plusHours(1);
        return tokenExpirationDate.isBefore(ZonedDateTime.now(ZoneId.of(pncpAuthProperties.getTimeZone())));
    }

    private String getLoginUrl() {
        return pncpAuthProperties.getBaseUrl() + PncpApiConstants.PNCP_LOGIN_V1;
    }

    private String getConsultAgencyUrl() {
        return pncpAuthProperties.getBaseUrl() + PncpApiConstants.PNCP_CONSULT_AGENCY_V1 + "/" + pncpAuthProperties.getCnpj();
    }

    private String getConsultUnitUrl() {
        return getConsultAgencyUrl() + PncpApiConstants.PNCP_CONSULT_UNIT_V1 + "/" + pncpAuthProperties.getUnitCode();
    }

    private String getConsultContratationUrl(Integer ano, Integer sequencial) {
        return getConsultAgencyUrl() + PncpApiConstants.PNCP_CONSULT_CONTRATATION_V1 + "/" + ano + "/" + sequencial;
    }
}