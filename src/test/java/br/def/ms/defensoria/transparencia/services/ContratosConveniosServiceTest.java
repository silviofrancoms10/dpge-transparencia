/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.services;

import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosRequest;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResource;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResponse;
import br.def.ms.defensoria.transparencia.models.entity.ContratosConvenios;
import br.def.ms.defensoria.transparencia.repositories.convenios.ContratosConveniosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContratosConveniosServiceTest {
    private ContratosConveniosService classUnderTest;
    private ContratosConveniosRepository contratosConveniosRepository;

    @BeforeEach
    void initMocks() {
        contratosConveniosRepository = mock(ContratosConveniosRepository.class);
        classUnderTest = new ContratosConveniosService(contratosConveniosRepository, new ModelMapper());
    }

    @Test
    void listarProcessosPorPeriodoTestSucess() {
        /* Preparation */
        int ano = 2023;
        List<ContratosConvenios> contratosConveniosList = createListContratosConvenios();

        /* Stubbing */
        when(contratosConveniosRepository.buscarContratosConveniosPorAno(any(Date.class), any(Date.class))).thenReturn(contratosConveniosList);

        /* Execution */
        ContratosConveniosResponse contratosConveniosResponse = classUnderTest.listarProcessosPorPeriodo(ano);

        /* Verification */
        assertNotNull(contratosConveniosResponse);
        verify(contratosConveniosRepository).buscarContratosConveniosPorAno(any(Date.class), any(Date.class));
        assertEquals(1, contratosConveniosResponse.getContratosConveniosData().size());
    }

    @Test
    void listarProcessosPorParametrosTestSucess() {
        /* Preparation */
        ContratosConveniosRequest contratosConveniosRequest = new ContratosConveniosRequest();
        ContratosConveniosResource contratosConveniosResource = ContratosConveniosResource.builder()
                .identificacaoUnica("/")
                .nomeContratada("CÂMARA")
                .modalidade("TCM")
                .encerrado(0)
                .build();
        contratosConveniosRequest.setContratosConveniosResource(contratosConveniosResource);

        List<ContratosConvenios> contratosConveniosList = createListContratosConvenios();

        /* Stubbing */
        when(contratosConveniosRepository.buscarContratosConveniosPorParametros(any(), any(), any(), any())).thenReturn(contratosConveniosList);

        /* Execution */
        ContratosConveniosResponse contratosConveniosResponse = classUnderTest.listarProcessosPorParametros(contratosConveniosRequest);

        /* Verification */
        assertNotNull(contratosConveniosResponse);
        verify(contratosConveniosRepository).buscarContratosConveniosPorParametros(any(), any(), any(), any());
        assertEquals(1, contratosConveniosResponse.getContratosConveniosData().size());
    }

    private List<ContratosConvenios> createListContratosConvenios() {
        ContratosConvenios contratosConvenios = new ContratosConvenios();
        contratosConvenios.setIdentificacaoUnica("/");
        contratosConvenios.setNomeContratada("CÂMARA");
        contratosConvenios.setModalidade("TCM");
        contratosConvenios.setEncerrado(0);

        return Collections.singletonList(contratosConvenios);
    }
}
