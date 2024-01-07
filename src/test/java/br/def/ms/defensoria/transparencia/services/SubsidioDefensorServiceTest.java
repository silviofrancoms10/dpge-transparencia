/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.services;

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.defensor.SubsidioDefensorResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioDefensor;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioDefensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubsidioDefensorServiceTest {
    private SubsidioDefensorService classUnderTest;
    private SubsidioDefensorRepository subsidioDefensorRepository;

    @BeforeEach
    void initMocks() {
        subsidioDefensorRepository = mock(SubsidioDefensorRepository.class);
        classUnderTest = new SubsidioDefensorService(subsidioDefensorRepository, new ModelMapper());
    }

    @Test
    void obterListaDeAnosParaSubsidioDeDefensor() {
        /* Stubbing */
        when(subsidioDefensorRepository.buscarListaAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> listaAnos = classUnderTest.listarAno();

        /* Verification */
        assertNotNull(listaAnos);
        assertFalse(listaAnos.isEmpty());
    }

    @Test
    void obterSubsidiosDeDefensorPorAno() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioDefensorRepository.buscarSubsidioDefensorPorAno(ano)).thenReturn(Collections.singletonList(mock(SubsidioDefensor.class)));

        /* Execution */
        SubsidioDefensorResponse response = classUnderTest.listarSubsidioDefensor(ano);

        /* Verification */
        assertNotNull(response);
        assertNotNull(response.getSubsidioDefensorData());
        assertFalse(response.getSubsidioDefensorData().isEmpty());
    }
}
