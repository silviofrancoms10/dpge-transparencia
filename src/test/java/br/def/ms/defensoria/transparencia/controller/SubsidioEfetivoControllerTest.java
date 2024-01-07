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

import br.def.ms.defensoria.transparencia.controllers.SubsidioEfetivoController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.efetivo.SubsidioEfetivoResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioEfetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SubsidioEfetivoControllerTest {
    private SubsidioEfetivoService subsidioEfetivoService;
    private SubsidioEfetivoController classUnderTest;

    @BeforeEach
    void initMocks() {
        subsidioEfetivoService = mock(SubsidioEfetivoService.class);
        classUnderTest = new SubsidioEfetivoController(subsidioEfetivoService, new ModelMapper());
    }

    @Test
    void testListarSubsidioEfetivo() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioEfetivoService.listarSubsidioEfetivo(ano)).thenReturn(new SubsidioEfetivoResponse());

        /* Execution */
        SubsidioEfetivoResponse result = classUnderTest.listarSubsidioEfetivo(ano);

        /* Verification */
        verify(subsidioEfetivoService).listarSubsidioEfetivo(ano);
        assertNotNull(result);
    }

    @Test
    void testListarAno() {
        /* Stubbing */
        when(subsidioEfetivoService.listarAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> result = classUnderTest.listarAno();

        /* Verification */
        verify(subsidioEfetivoService).listarAno();
        assertNotNull(result);
    }
}