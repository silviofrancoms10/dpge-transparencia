/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.controller;

import br.def.ms.defensoria.transparencia.controllers.DiariasController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResponse;
import br.def.ms.defensoria.transparencia.services.DiariasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DiariasControllerTest {
    private DiariasService diariasService;
    private DiariasController classUnderTest;

    @BeforeEach
    void initMocks() {
        diariasService = mock(DiariasService.class);
        classUnderTest = new DiariasController(diariasService, new ModelMapper());
    }

    @Test
    void testListarDiariasPorPeriodo() {
        /* Preparation */
        DiariasRequest diariasRequest = new DiariasRequest();
        DiariasResource diariasResource = DiariasResource.builder().mes(1).ano(2021).build();
        diariasRequest.setDiariasResource(diariasResource);

        /* Stubbing */
        when(diariasService.listarDiariasPorPeriodo(diariasRequest)).thenReturn(new DiariasResponse());

        /* Execution */
        DiariasResponse result = classUnderTest.listarDiariasPorPeriodo(diariasRequest);

        /* Verification */
        verify(diariasService).listarDiariasPorPeriodo(diariasRequest);
        assertNotNull(result);
    }
}
