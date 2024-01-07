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

import br.def.ms.defensoria.transparencia.controllers.SubsidioCargosComissaoController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.cargosComissao.SubsidioCargosComissaoResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioCargosComissaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SubsidioCargosComissaoControllerTest {
    private SubsidioCargosComissaoService subsidioCargosComissaoService;
    private SubsidioCargosComissaoController classUnderTest;

    @BeforeEach
    void initMocks() {
        subsidioCargosComissaoService = mock(SubsidioCargosComissaoService.class);
        classUnderTest = new SubsidioCargosComissaoController(subsidioCargosComissaoService, new ModelMapper());
    }

    @Test
    void testListarSubsidioCargosComissao() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioCargosComissaoService.listarSubsidioCargosComissao(ano)).thenReturn(new SubsidioCargosComissaoResponse());

        /* Execution */
        SubsidioCargosComissaoResponse result = classUnderTest.listarSubsidioCargosComissao(ano);

        /* Verification */
        verify(subsidioCargosComissaoService).listarSubsidioCargosComissao(ano);
        assertNotNull(result);
    }

    @Test
    void testListarAno() {
        /* Stubbing */
        when(subsidioCargosComissaoService.listarAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> result = classUnderTest.listarAno();

        /* Verification */
        verify(subsidioCargosComissaoService).listarAno();
        assertNotNull(result);
    }
}