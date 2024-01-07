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

import br.def.ms.defensoria.transparencia.controllers.integration.PncpIntegrationController;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.ContratacaoData;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.UnidadeData;
import br.def.ms.defensoria.transparencia.services.integration.PncpIntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PncpIntegrationControllerTest {

    private PncpIntegrationService pncpIntegrationService;
    private PncpIntegrationController classUnderTest;

    @BeforeEach
    void initMocks() {
        pncpIntegrationService = mock(PncpIntegrationService.class);
        classUnderTest = new PncpIntegrationController(pncpIntegrationService);
    }

    @Test
    void testLogin() {
        /* Preparation */
        HttpHeaders mockHeaders = new HttpHeaders();
        mockHeaders.add("Authorization", "Bearer teste");

        /* Stubbing */
        when(pncpIntegrationService.loginMethod()).thenReturn(mockHeaders);

        /* Execution */
        HttpHeaders result = classUnderTest.login();

        /* Verification */
        verify(pncpIntegrationService).loginMethod();
        assertNotNull(result);
        assertNotNull(result.get("Authorization"));
    }

    @Test
    void testConsultUnit() {
        /* Preparation */
        UnidadeData mockUnidadeData = new UnidadeData();

        /* Stubbing */
        when(pncpIntegrationService.consultUnitMethod()).thenReturn(mockUnidadeData);

        /* Execution */
        UnidadeData result = classUnderTest.consultUnit();

        /* Verification */
        verify(pncpIntegrationService).consultUnitMethod();
        assertNotNull(result);
    }

    @Test
    void testConsultContratation() {
        /* Preparation */
        Integer mockAno = 2021;
        Integer mockSequencial = 1;
        ContratacaoData mockContratacaoData = new ContratacaoData();

        /* Stubbing */
        when(pncpIntegrationService.consultContratationMethod(mockAno, mockSequencial)).thenReturn(mockContratacaoData);

        /* Execution */
        classUnderTest.consultContratation(mockAno, mockSequencial);

        /* Verification */
        verify(pncpIntegrationService).consultContratationMethod(mockAno, mockSequencial);
    }
}
