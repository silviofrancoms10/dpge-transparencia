/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.services;

import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.ConsultaDeRemuneracao;
import br.def.ms.defensoria.transparencia.models.entity.ConsultaRemuneracaoId;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import br.def.ms.defensoria.transparencia.repositories.servidores.ConsultaDeRemuneracaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConsultaDeRemuneracaoServiceTest {
    private ConsultaDeRemuneracaoService classUnderTest;
    private ConsultaDeRemuneracaoRepository consultaDeRemuneracaoRepository;

    @BeforeEach
    void initMocks() {
        consultaDeRemuneracaoRepository = mock(ConsultaDeRemuneracaoRepository.class);
        classUnderTest = new ConsultaDeRemuneracaoService(consultaDeRemuneracaoRepository, new ModelMapper());
    }

    @Test
    void listarConsultaDeRemuneracaoPorFiltrosTestSuccess() {
        /* Preparation */
        ConsultaDeRemuneracaoRequest consultaDeRemuneracaoRequest = new ConsultaDeRemuneracaoRequest();
        ConsultaDeRemuneracaoResource consultaDeRemuneracaoResource = ConsultaDeRemuneracaoResource.builder()
                .mes(1)
                .ano(2023)
                .categoriaServidor(EnumTipoServidor.DEFENSOR)
                .build();
        consultaDeRemuneracaoRequest.setConsultaDeRemuneracaoResource(consultaDeRemuneracaoResource);

        List<ConsultaDeRemuneracao> consultaDeRemuneracaoList = new ArrayList<>();
        ConsultaDeRemuneracao consultaDeRemuneracao = new ConsultaDeRemuneracao();
        consultaDeRemuneracao.setCodBeneficiario(32);
        consultaDeRemuneracao.setCategoriaServidor(EnumTipoServidor.DEFENSOR);
        consultaDeRemuneracao.setId(mock(ConsultaRemuneracaoId.class));
        consultaDeRemuneracaoList.add(consultaDeRemuneracao);

        /* Stubbing */
        when(consultaDeRemuneracaoRepository.buscarConsultaDeRemuneracaoPorFiltros(any(Integer.class), any(Integer.class), any(EnumTipoServidor.class))).thenReturn(consultaDeRemuneracaoList);

        /* Execution */
        ConsultaDeRemuneracaoResponse consultaDeRemuneracaoResponse = classUnderTest.listarConsultaDeRemuneracaoPorFiltros(consultaDeRemuneracaoRequest);

        /* Verification */
        verify(consultaDeRemuneracaoRepository, atMostOnce()).buscarConsultaDeRemuneracaoPorFiltros(any(Integer.class), any(Integer.class), any(EnumTipoServidor.class));
        assertNotNull(consultaDeRemuneracaoResponse);
        assertEquals(1, consultaDeRemuneracaoResponse.getConsultaDeRemuneracaoData().size());
    }
}
