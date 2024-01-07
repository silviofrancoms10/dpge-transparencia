/*
 *   Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *   Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *   forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *   or circumvent any protection mechanism; to sell, license, lease, rent,
 *   redistribute or make accessible to any third party, whether for profit or
 *   without charge.
 */

package br.def.ms.defensoria.transparencia.services.integration;

import br.def.ms.defensoria.transparencia.configuration.SpfApiConstants;
import br.def.ms.defensoria.transparencia.models.integration.dto.spf.LoginData;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class SpfIntegrationService {
    private final SpfAuthProperties spfAuthProperties;
    private final RestTemplate restTemplate = new RestTemplate();
    private LoginData loginData;

    public SpfIntegrationService(SpfAuthProperties spfAuthProperties) {
        this.spfAuthProperties = spfAuthProperties;
    }

    public LoginData loginMethod() {
        HttpEntity<SpfAuthProperties> requestEntity = new HttpEntity<>(spfAuthProperties);
        return restTemplate.postForObject(getLoginUrl(), requestEntity, LoginData.class);
    }

    private String getLoginUrl() {
        return spfAuthProperties.getBaseUrl() + SpfApiConstants.SPF_LOGIN_V1;
    }
}
