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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.CargosComissao;
import br.def.ms.defensoria.transparencia.repositories.servidores.CargosComissaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CargosComissaoServiceTest {
    private CargosComissaoService classUnderTest;
    private CargosComissaoRepository cargosComissaoRepository;

    @BeforeEach
    void initMocks() {
        cargosComissaoRepository = mock(CargosComissaoRepository.class);
        classUnderTest = new CargosComissaoService(cargosComissaoRepository, new ModelMapper());
    }

    @Test
    void listarCargosComissaoTestSuccess() {
        /* Preparation */
        CargosComissaoRequest cargosComissaoRequest = new CargosComissaoRequest();
        cargosComissaoRequest.setCargosComissaoResource(null);

        List<CargosComissao> cargosComissaoList = new ArrayList<>();
        CargosComissao cargosComissao = new CargosComissao();
        cargosComissao.setIdBeneficiario(373);
        cargosComissaoList.add(cargosComissao);

        /* Stubbing */
        when(cargosComissaoRepository.findAll()).thenReturn(cargosComissaoList);

        /* Execution */
        CargosComissaoResponse cargosComissaoResponse = classUnderTest.listarCargosComissao();

        /* Verification */
        assertNotNull(cargosComissaoResponse);
        assertEquals(1, cargosComissaoResponse.getCargosComissaoData().size());
    }

    @Test
    void listarCargosEfetivosPorPeriodoTestSuccess() {
        /* Preparation */
        CargosComissaoRequest cargosComissaoRequest = new CargosComissaoRequest();
        CargosComissaoResource cargosComissaoResource = CargosComissaoResource.builder().mes(7).ano(2023).build();
        cargosComissaoRequest.setCargosComissaoResource(cargosComissaoResource);

        List<CargosComissao> cargosComissaoList = new ArrayList<>();
        CargosComissao cargosComissao = new CargosComissao();
        cargosComissao.setIdBeneficiario(373);
        cargosComissaoList.add(cargosComissao);

        /* Stubbing */
        when(cargosComissaoRepository.buscarCargosComissaoPorPeriodo(any(Date.class), any(Date.class))).thenReturn(cargosComissaoList);

        /* Execution */
        CargosComissaoResponse cargosComissaoResponse = classUnderTest.listarCargosComissaoPorPeriodo(cargosComissaoRequest);

        /* Verification */
        assertNotNull(cargosComissaoResponse);
        assertEquals(1, cargosComissaoResponse.getCargosComissaoData().size());
    }
}
