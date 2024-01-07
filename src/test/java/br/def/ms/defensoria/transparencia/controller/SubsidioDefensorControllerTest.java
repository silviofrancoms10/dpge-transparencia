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

import br.def.ms.defensoria.transparencia.controllers.SubsidioDefensorController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.defensor.SubsidioDefensorResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioDefensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SubsidioDefensorControllerTest {
    private SubsidioDefensorService subsidioDefensorService;
    private SubsidioDefensorController classUnderTest;

    @BeforeEach
    void initMocks() {
        subsidioDefensorService = mock(SubsidioDefensorService.class);
        classUnderTest = new SubsidioDefensorController(subsidioDefensorService, new ModelMapper());
    }

    @Test
    void testListarSubsidioDefensor() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioDefensorService.listarSubsidioDefensor(ano)).thenReturn(new SubsidioDefensorResponse());

        /* Execution */
        SubsidioDefensorResponse result = classUnderTest.listarSubsidioDefensor(ano);

        /* Verification */
        verify(subsidioDefensorService).listarSubsidioDefensor(ano);
        assertNotNull(result);
    }

    @Test
    void testListarAno() {
        /* Stubbing */
        when(subsidioDefensorService.listarAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> result = classUnderTest.listarAno();

        /* Verification */
        verify(subsidioDefensorService).listarAno();
        assertNotNull(result);
    }
}