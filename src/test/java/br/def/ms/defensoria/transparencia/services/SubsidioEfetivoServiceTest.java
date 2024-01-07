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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.efetivo.SubsidioEfetivoResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioEfetivo;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioEfetivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubsidioEfetivoServiceTest {
    private SubsidioEfetivoService classUnderTest;
    private SubsidioEfetivoRepository subsidioEfetivoRepository;

    @BeforeEach
    void initMocks() {
        subsidioEfetivoRepository = mock(SubsidioEfetivoRepository.class);
        classUnderTest = new SubsidioEfetivoService(subsidioEfetivoRepository, new ModelMapper());
    }

    @Test
    void obterListaAnosSubsidioEfetivo() {
        /* Stubbing */
        when(subsidioEfetivoRepository.buscarListaAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> listaAnos = classUnderTest.listarAno();

        /* Verification */
        assertNotNull(listaAnos);
        assertFalse(listaAnos.isEmpty());
    }

    @Test
    void obterSubsidioEfetivoPorAno() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioEfetivoRepository.buscarSubsidioEfetivoPorAno(ano)).thenReturn(Collections.singletonList(mock(SubsidioEfetivo.class)));

        /* Execution */
        SubsidioEfetivoResponse response = classUnderTest.listarSubsidioEfetivo(ano);

        /* Verification */
        assertNotNull(response);
        assertNotNull(response.getSubsidioEfetivoData());
        assertFalse(response.getSubsidioEfetivoData().isEmpty());
    }
}
