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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResponse;
import br.def.ms.defensoria.transparencia.models.entity.CargosEfetivos;
import br.def.ms.defensoria.transparencia.repositories.servidores.CargosEfetivosRepository;
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

class CargosEfetivosServiceTest {
    private CargosEfetivosService classUnderTest;
    private CargosEfetivosRepository cargosEfetivosRepository;

    @BeforeEach
    void initMocks() {
        cargosEfetivosRepository = mock(CargosEfetivosRepository.class);
        classUnderTest = new CargosEfetivosService(cargosEfetivosRepository, new ModelMapper());
    }

    @Test
    void listarCargosEfetivosTestSuccess() {
        /* Preparation */
        CargosEfetivosRequest cargosEfetivosRequest = new CargosEfetivosRequest();
        cargosEfetivosRequest.setCargosEfetivosResource(null);

        List<CargosEfetivos> cargosEfetivosList = new ArrayList<>();
        CargosEfetivos cargosEfetivos = new CargosEfetivos();
        cargosEfetivos.setIdCarreira(25);
        cargosEfetivosList.add(cargosEfetivos);

        /* Stubbing */
        when(cargosEfetivosRepository.findAll()).thenReturn(cargosEfetivosList);

        /* Execution */
        CargosEfetivosResponse cargosEfetivosResponse = classUnderTest.listarCargosEfetivos();

        /* Verification */
        assertNotNull(cargosEfetivosResponse);
        assertEquals(1, cargosEfetivosResponse.getCargosEfetivosData().size());
    }

    @Test
    void listarCargosEfetivosPorPeriodoTestSuccess() {
        /* Preparation */
        CargosEfetivosRequest cargosEfetivosRequest = new CargosEfetivosRequest();
        CargosEfetivosResource cargosEfetivosResource = CargosEfetivosResource.builder().mes(7).ano(2023).build();
        cargosEfetivosRequest.setCargosEfetivosResource(cargosEfetivosResource);

        List<CargosEfetivos> cargosEfetivosList = new ArrayList<>();
        CargosEfetivos cargosEfetivos = new CargosEfetivos();
        cargosEfetivos.setIdCarreira(25);
        cargosEfetivosList.add(cargosEfetivos);

        /* Stubbing */
        when(cargosEfetivosRepository.buscarCargosEfetivosPorPeriodo(any(Date.class), any(Date.class))).thenReturn(cargosEfetivosList);

        /* Execution */
        CargosEfetivosResponse cargosEfetivosResponse = classUnderTest.listarCargosEfetivosPorPeriodo(cargosEfetivosRequest);

        /* Verification */
        assertNotNull(cargosEfetivosResponse);
        assertEquals(1, cargosEfetivosResponse.getCargosEfetivosData().size());
    }
}
