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

import br.def.ms.defensoria.transparencia.controllers.CargosComissaoController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResponse;
import br.def.ms.defensoria.transparencia.services.CargosComissaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CargosComissaoControllerTest {
    private CargosComissaoService cargosComissaoService;
    private CargosComissaoController classUnderTest;

    @BeforeEach
    void initMocks() {
        cargosComissaoService = mock(CargosComissaoService.class);
        classUnderTest = new CargosComissaoController(cargosComissaoService, new ModelMapper());
    }

    @Test
    void testListarCargosComissao() {
        /* Preparation */
        CargosComissaoRequest cargosComissaoRequest = new CargosComissaoRequest();
        cargosComissaoRequest.setCargosComissaoResource(null);

        /* Stubbing */
        when(cargosComissaoService.listarCargosComissao())
                .thenReturn(new CargosComissaoResponse());

        /* Execution */
        CargosComissaoResponse result = classUnderTest.listarCargosComissao();

        /* Verification */
        verify(cargosComissaoService).listarCargosComissao();
        assertNotNull(result);
    }

    @Test
    void testListarCargosComissaoPorPeriodo() {
        /* Preparation */
        CargosComissaoRequest cargosComissaoRequest = new CargosComissaoRequest();
        CargosComissaoResource cargosComissaoResource = CargosComissaoResource.builder().mes(7).ano(2023).build();
        cargosComissaoRequest.setCargosComissaoResource(cargosComissaoResource);

        /* Stubbing */
        when(cargosComissaoService.listarCargosComissaoPorPeriodo(cargosComissaoRequest)).thenReturn(new CargosComissaoResponse());

        /* Execution */
        CargosComissaoResponse result = classUnderTest.listarCargosComissaoPorPeriodo(cargosComissaoRequest);

        /* Verification */
        verify(cargosComissaoService).listarCargosComissaoPorPeriodo(cargosComissaoRequest);
        assertNotNull(result);
    }
}