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

import br.def.ms.defensoria.transparencia.controllers.CargosEfetivosController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResponse;
import br.def.ms.defensoria.transparencia.services.CargosEfetivosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CargosEfetivosControllerTest {
    private CargosEfetivosService cargosEfetivosService;
    private CargosEfetivosController classUnderTest;

    @BeforeEach
    void initMocks() {
        cargosEfetivosService = mock(CargosEfetivosService.class);
        classUnderTest = new CargosEfetivosController(cargosEfetivosService, new ModelMapper());
    }

    @Test
    void testListarCargosEfetivos() {
        /* Preparation */
        CargosEfetivosRequest cargosEfetivosRequest = new CargosEfetivosRequest();
        cargosEfetivosRequest.setCargosEfetivosResource(null);

        /* Stubbing */
        when(cargosEfetivosService.listarCargosEfetivos())
                .thenReturn(new CargosEfetivosResponse());

        /* Execution */
        CargosEfetivosResponse result = classUnderTest.listarCargosEfetivos();

        /* Verification */
        verify(cargosEfetivosService).listarCargosEfetivos();
        assertNotNull(result);
    }

    @Test
    void testListarCargosEfetivosPorPeriodo() {
        /* Preparation */
        CargosEfetivosRequest cargosEfetivosRequest = new CargosEfetivosRequest();
        CargosEfetivosResource cargosEfetivosResource = CargosEfetivosResource.builder().mes(7).ano(2023).build();
        cargosEfetivosRequest.setCargosEfetivosResource(cargosEfetivosResource);

        /* Stubbing */
        when(cargosEfetivosService.listarCargosEfetivosPorPeriodo(cargosEfetivosRequest)).thenReturn(new CargosEfetivosResponse());

        /* Execution */
        CargosEfetivosResponse result = classUnderTest.listarCargosEfetivosPorPeriodo(cargosEfetivosRequest);

        /* Verification */
        verify(cargosEfetivosService).listarCargosEfetivosPorPeriodo(cargosEfetivosRequest);
        assertNotNull(result);
    }
}