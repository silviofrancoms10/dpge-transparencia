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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.cargosComissao.SubsidioCargosComissaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioCargosComissao;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioCargosComissaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubsidioCargosComissaoServiceTest {
    private SubsidioCargosComissaoService classUnderTest;
    private SubsidioCargosComissaoRepository subsidioCargosComissaoRepository;

    @BeforeEach
    void initMocks() {
        subsidioCargosComissaoRepository = mock(SubsidioCargosComissaoRepository.class);
        classUnderTest = new SubsidioCargosComissaoService(subsidioCargosComissaoRepository, new ModelMapper());
    }

    @Test
    void obterListaDeAnosParaSubsidioCargosComissao() {
        /* Stubbing */
        when(subsidioCargosComissaoRepository.buscarListaAno()).thenReturn(mock(List.class));

        /* Execution */
        List<Integer> listaAnos = classUnderTest.listarAno();

        /* Verification */
        assertNotNull(listaAnos);
        assertFalse(listaAnos.isEmpty());
    }

    @Test
    void obterSubsidioCargosComissaoPorAno() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(subsidioCargosComissaoRepository.buscarSubsidioCargosComissaoPorAno(ano)).thenReturn(Collections.singletonList(mock(SubsidioCargosComissao.class)));

        /* Execution */
        SubsidioCargosComissaoResponse response = classUnderTest.listarSubsidioCargosComissao(ano);

        /* Verification */
        assertNotNull(response);
        assertNotNull(response.getSubsidioCargosComissaoData());
        assertFalse(response.getSubsidioCargosComissaoData().isEmpty());
    }
}
