/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.controller;

import br.def.ms.defensoria.transparencia.controllers.ContratosConveniosController;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosRequest;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResource;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResponse;
import br.def.ms.defensoria.transparencia.services.ContratosConveniosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ContratosConveniosControllerTest {
    private ContratosConveniosService contratosConveniosService;
    private ContratosConveniosController classUnderTest;

    @BeforeEach
    void initMocks() {
        contratosConveniosService = mock(ContratosConveniosService.class);
        classUnderTest = new ContratosConveniosController(contratosConveniosService, new ModelMapper());
    }

    @Test
    void testListarProcessosPorPeriodo() {
        /* Preparation */
        int ano = 2023;

        /* Stubbing */
        when(contratosConveniosService.listarProcessosPorPeriodo(ano)).thenReturn(new ContratosConveniosResponse());

        /* Execution */
        ContratosConveniosResponse result = classUnderTest.listarProcessosPorPeriodo(ano);

        /* Verification */
        verify(contratosConveniosService).listarProcessosPorPeriodo(ano);
        assertNotNull(result);
    }

    @Test
    void testListarProcessosPorParametrosWhenSuccess() {
        /* Preparation */
        ContratosConveniosRequest contratosConveniosRequest = new ContratosConveniosRequest();
        ContratosConveniosResource contratosConveniosResource = ContratosConveniosResource.builder()
                .identificacaoUnica("/")
                .nomeContratada("CÂMARA")
                .modalidade("TCM")
                .encerrado(1)
                .build();
        contratosConveniosRequest.setContratosConveniosResource(contratosConveniosResource);

        /* Stubbing */
        when(contratosConveniosService.listarProcessosPorParametros(contratosConveniosRequest)).thenReturn(new ContratosConveniosResponse());

        /* Execution */
        ContratosConveniosResponse result = classUnderTest.listarProcessosPorParametros(contratosConveniosRequest);

        /* Verification */
        verify(contratosConveniosService).listarProcessosPorParametros(contratosConveniosRequest);
        assertNotNull(result);
    }
}
