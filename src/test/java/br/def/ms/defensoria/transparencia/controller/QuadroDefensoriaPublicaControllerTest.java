/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.controller;

import br.def.ms.defensoria.transparencia.controllers.QuadroDefensoriaPublicaController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResponse;
import br.def.ms.defensoria.transparencia.services.QuadroDefensoriaPublicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class QuadroDefensoriaPublicaControllerTest {
    private QuadroDefensoriaPublicaService quadroDefensoriaPublicaService;
    private QuadroDefensoriaPublicaController classUnderTest;

    @BeforeEach
    void initMocks() {
        quadroDefensoriaPublicaService = mock(QuadroDefensoriaPublicaService.class);
        classUnderTest = new QuadroDefensoriaPublicaController(quadroDefensoriaPublicaService, new ModelMapper());
    }

    @Test
    void testListarQuadroDefensoriaPublica() {
        /* Preparation */
        QuadroDefensoriaPublicaRequest quadroDefensoriaPublicaRequest = new QuadroDefensoriaPublicaRequest();
        quadroDefensoriaPublicaRequest.setQuadroDefensoriaPublicaResource(null);

        /* Stubbing */
        when(quadroDefensoriaPublicaService.listarQuadroDefensoriaPublica())
                .thenReturn(new QuadroDefensoriaPublicaResponse());

        /* Execution */
        QuadroDefensoriaPublicaResponse result = classUnderTest.listarQuadroDefensoriaPublica();

        /* Verification */
        verify(quadroDefensoriaPublicaService).listarQuadroDefensoriaPublica();
        assertNotNull(result);
    }

    @Test
    void testListarQuadroDefensoriaPublicaPorPeriodo() {
        /* Preparation */
        QuadroDefensoriaPublicaRequest quadroDefensoriaPublicaRequest = new QuadroDefensoriaPublicaRequest();
        QuadroDefensoriaPublicaResource quadroDefensoriaPublicaResource = QuadroDefensoriaPublicaResource.builder().mes(1).ano(2021).build();
        quadroDefensoriaPublicaRequest.setQuadroDefensoriaPublicaResource(quadroDefensoriaPublicaResource);

        /* Stubbing */
        when(quadroDefensoriaPublicaService.listarQuadroDefensoriaPublicaPorPeriodo(quadroDefensoriaPublicaRequest)).thenReturn(new QuadroDefensoriaPublicaResponse());

        /* Execution */
        QuadroDefensoriaPublicaResponse result = classUnderTest.listarQuadroDefensoriaPublicaPorPeriodo(quadroDefensoriaPublicaRequest);

        /* Verification */
        verify(quadroDefensoriaPublicaService).listarQuadroDefensoriaPublicaPorPeriodo(quadroDefensoriaPublicaRequest);
        assertNotNull(result);
    }
}
