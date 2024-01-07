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

import br.def.ms.defensoria.transparencia.controllers.ConsultaDeRemuneracaoController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResponse;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import br.def.ms.defensoria.transparencia.services.ConsultaDeRemuneracaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ConsultaDeRemuneracaoControllerTest {
    private ConsultaDeRemuneracaoService consultaDeRemuneracaoService;
    private ConsultaDeRemuneracaoController classUnderTest;

    @BeforeEach
    void initMocks(){
        consultaDeRemuneracaoService = mock(ConsultaDeRemuneracaoService.class);
        classUnderTest = new ConsultaDeRemuneracaoController(consultaDeRemuneracaoService, new ModelMapper());
    }

    @Test
    void testListarConsultaDeRemuneracao() {
        /* Preparation */
        ConsultaDeRemuneracaoRequest consultaDeRemuneracaoRequest = new ConsultaDeRemuneracaoRequest();
        ConsultaDeRemuneracaoResource consultaDeRemuneracaoResource = ConsultaDeRemuneracaoResource.builder()
                .mes(7)
                .ano(2023)
                .categoriaServidor(EnumTipoServidor.DEFENSOR)
                .build();
        consultaDeRemuneracaoRequest.setConsultaDeRemuneracaoResource(consultaDeRemuneracaoResource);

        /* Stubbing */
        when(consultaDeRemuneracaoService.listarConsultaDeRemuneracaoPorFiltros(consultaDeRemuneracaoRequest))
                .thenReturn(new ConsultaDeRemuneracaoResponse());

        /* Execution */
        ConsultaDeRemuneracaoResponse result = classUnderTest.listarConsultaDeRemuneracao(consultaDeRemuneracaoRequest);

        /* Verification */
        verify(consultaDeRemuneracaoService).listarConsultaDeRemuneracaoPorFiltros(consultaDeRemuneracaoRequest);
        assertNotNull(result);
    }
}
