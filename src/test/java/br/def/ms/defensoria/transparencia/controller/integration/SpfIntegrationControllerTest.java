/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.controller.integration;

import br.def.ms.defensoria.transparencia.controllers.integration.SpfIntegrationController;
import br.def.ms.defensoria.transparencia.models.integration.dto.spf.DadosRetornoData;
import br.def.ms.defensoria.transparencia.models.integration.dto.spf.LoginData;
import br.def.ms.defensoria.transparencia.models.integration.dto.spf.StatusRetornoData;
import br.def.ms.defensoria.transparencia.services.integration.SpfIntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SpfIntegrationControllerTest {

    private SpfIntegrationService spfIntegrationService;
    private SpfIntegrationController classUnderTest;

    @BeforeEach
    void initMocks() {
        spfIntegrationService = mock(SpfIntegrationService.class);
        classUnderTest = new SpfIntegrationController(spfIntegrationService);
    }

    @Test
    void testLogin() {
        /* Preparation */
        LoginData mockLoginData = new LoginData();
        mockLoginData.setStatus(StatusRetornoData.builder().code(200).build());

        /* Stubbing */
        when(spfIntegrationService.loginMethod()).thenReturn(mockLoginData);

        /* Execution */
        LoginData result = classUnderTest.login();

        /* Verification */
        verify(spfIntegrationService).loginMethod();
        assertNotNull(result);
        assertNotNull(result.getStatus());
    }
}
