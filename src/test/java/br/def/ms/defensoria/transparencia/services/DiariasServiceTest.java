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

import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResponse;
import br.def.ms.defensoria.transparencia.models.entity.Diarias;
import br.def.ms.defensoria.transparencia.repositories.servidores.DiariasRepository;
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

public class DiariasServiceTest {
    private DiariasService classUnderTest;
    private DiariasRepository diariasRepository;

    @BeforeEach
    void initMocks() {
        diariasRepository = mock(DiariasRepository.class);
        classUnderTest = new DiariasService(diariasRepository, new ModelMapper());
    }

    @Test
    void listarDiariasPorPeriodoTestSuccess() {
        /* Preparation */
        DiariasRequest diariasRequest = new DiariasRequest();
        DiariasResource diariasResource = DiariasResource.builder().mes(1).ano(2021).build();
        diariasRequest.setDiariasResource(diariasResource);

        List<Diarias> diariasList = new ArrayList<>();
        Diarias diarias = new Diarias();
        diarias.setMatricula("1234");
        diariasList.add(diarias);

        /* Stubbing */
        when(diariasRepository.buscarDiariasPorPeriodo(any(Date.class), any(Date.class))).thenReturn(diariasList);

        /* Execution */
        DiariasResponse diariasResponse = classUnderTest.listarDiariasPorPeriodo(diariasRequest);

        /* Verification */
        assertNotNull(diariasResponse);
        assertEquals(1, diariasResponse.getDiariasData().size());
    }
}
