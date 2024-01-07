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

import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResponse;
import br.def.ms.defensoria.transparencia.models.entity.QuadroDefensoriaPublica;
import br.def.ms.defensoria.transparencia.repositories.servidores.QuadroDefensoriaPublicaRepository;
import org.modelmapper.ModelMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QuadroDefensoriaPublicaService {
    private final QuadroDefensoriaPublicaRepository quadroDefensoriaPublicaRepository;
    private final ModelMapper modelMapper;
    private Date dataInicio, dataFim;

    public QuadroDefensoriaPublicaService(QuadroDefensoriaPublicaRepository quadroDefensoriaPublicaRepository, ModelMapper modelMapper) {
        this.quadroDefensoriaPublicaRepository = quadroDefensoriaPublicaRepository;
        this.modelMapper = modelMapper;
    }

    public QuadroDefensoriaPublicaResponse listarQuadroDefensoriaPublica() {
        QuadroDefensoriaPublicaResponse quadroDefensoriaPublicaResponse = new QuadroDefensoriaPublicaResponse();
        List<QuadroDefensoriaPublica> listQuadroDefensoriaPublica = quadroDefensoriaPublicaRepository.findAll();

        List<QuadroDefensoriaPublicaData> data = listQuadroDefensoriaPublica.stream()
                .map(quadroDefensoriaPublica -> modelMapper.map(quadroDefensoriaPublica, QuadroDefensoriaPublicaData.class))
                .collect(Collectors.toList());

        quadroDefensoriaPublicaResponse.setQuadroDefensoriaPublicaData(data);

        return quadroDefensoriaPublicaResponse;
    }

    public QuadroDefensoriaPublicaResponse listarQuadroDefensoriaPublicaPorPeriodo(QuadroDefensoriaPublicaRequest request) {
    prepararDatasDoPeriodo(request.getQuadroDefensoriaPublicaResource().getMes(), request.getQuadroDefensoriaPublicaResource().getAno());

    QuadroDefensoriaPublicaResponse quadroDefensoriaPublicaResponse = new QuadroDefensoriaPublicaResponse();
    List<QuadroDefensoriaPublica> listQuadroDefensoriaPublica = quadroDefensoriaPublicaRepository.buscarQuadroDefensoriaPublicaPorPeriodo(dataInicio, dataFim);

    List<QuadroDefensoriaPublicaData> data = listQuadroDefensoriaPublica.stream()
            .map(quadroDefensoriaPublica -> modelMapper.map(quadroDefensoriaPublica, QuadroDefensoriaPublicaData.class))
            .collect(Collectors.toList());
    quadroDefensoriaPublicaResponse.setQuadroDefensoriaPublicaData(data);

    return quadroDefensoriaPublicaResponse;
    }

    public void prepararDatasDoPeriodo(Integer mes, Integer ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, 1); // Mês começa em 0 no Calendar (janeiro é 0)
        this.dataInicio = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataFim = calendar.getTime();
    }
}
