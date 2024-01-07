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

import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResponse;
import br.def.ms.defensoria.transparencia.models.entity.QuadroDefensoriaPublica;
import br.def.ms.defensoria.transparencia.repositories.servidores.QuadroDefensoriaPublicaRepository;
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

class QuadroDefensoriaPublicaServiceTest {
    private QuadroDefensoriaPublicaService classUnderTest;
    private QuadroDefensoriaPublicaRepository quadroDefensoriaPublicaRepository;

    @BeforeEach
    void initMocks() {
        quadroDefensoriaPublicaRepository = mock(QuadroDefensoriaPublicaRepository.class);
        classUnderTest = new QuadroDefensoriaPublicaService(quadroDefensoriaPublicaRepository, new ModelMapper());
    }

    @Test
    void listarQuadroDefensoriaPublicaTestSuccess() {
        /* Preparation */
        QuadroDefensoriaPublicaRequest quadroDefensoriaPublicaRequest = new QuadroDefensoriaPublicaRequest();
        quadroDefensoriaPublicaRequest.setQuadroDefensoriaPublicaResource(null);

        List<QuadroDefensoriaPublica> defensoriaPublicaList = new ArrayList<>();
        QuadroDefensoriaPublica quadroDefensoriaPublica = new QuadroDefensoriaPublica();
        quadroDefensoriaPublica.setIdCarreira(25);
        defensoriaPublicaList.add(quadroDefensoriaPublica);

        /* Stubbing */
        when(quadroDefensoriaPublicaRepository.findAll()).thenReturn(defensoriaPublicaList);

        /* Execution */
        QuadroDefensoriaPublicaResponse quadroDefensoriaPublicaResponse = classUnderTest.listarQuadroDefensoriaPublica();

        /* Verification */
        assertNotNull(quadroDefensoriaPublicaResponse);
        assertEquals(1, quadroDefensoriaPublicaResponse.getQuadroDefensoriaPublicaData().size());
    }

    @Test
    void listarQuadroDefensoriaPublicaPorPeriodoTestSucess(){
        /* Preparation */
        QuadroDefensoriaPublicaRequest quadroDefensoriaPublicaRequest = new QuadroDefensoriaPublicaRequest();
        QuadroDefensoriaPublicaResource quadroDefensoriaPublicaResource = QuadroDefensoriaPublicaResource.builder().mes(7).ano(2023).build();
        quadroDefensoriaPublicaRequest.setQuadroDefensoriaPublicaResource(quadroDefensoriaPublicaResource);

        List<QuadroDefensoriaPublica> defensoriaPublicaList = new ArrayList<>();
        QuadroDefensoriaPublica quadroDefensoriaPublica = new QuadroDefensoriaPublica();
        quadroDefensoriaPublica.setIdCarreira(2);
        defensoriaPublicaList.add(quadroDefensoriaPublica);

        /* Stubbing */
        when(quadroDefensoriaPublicaRepository.buscarQuadroDefensoriaPublicaPorPeriodo(any(Date.class), any(Date.class))).thenReturn(defensoriaPublicaList);

        /* Execution */
        QuadroDefensoriaPublicaResponse quadroDefensoriaPublicaResponse = classUnderTest.listarQuadroDefensoriaPublicaPorPeriodo(quadroDefensoriaPublicaRequest);

        /* Verification */
        assertNotNull(quadroDefensoriaPublicaResponse);
        assertEquals(1, quadroDefensoriaPublicaResponse.getQuadroDefensoriaPublicaData().size());
    }
}
