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

import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResponse;
import br.def.ms.defensoria.transparencia.models.entity.Diarias;
import br.def.ms.defensoria.transparencia.repositories.servidores.DiariasRepository;
import org.modelmapper.ModelMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DiariasService {

    private final DiariasRepository diariasRepository;
    private final ModelMapper modelMapper;
    private Date dataInicio, dataFim;

    public DiariasService(DiariasRepository diariasRepository, ModelMapper modelMapper) {
        this.diariasRepository = diariasRepository;
        this.modelMapper = modelMapper;
    }

    public DiariasResponse listarDiariasPorPeriodo(DiariasRequest request) {
        prepararDatasDoPeriodo(request.getDiariasResource().getMes(), request.getDiariasResource().getAno());

        DiariasResponse diariasResponse = new DiariasResponse();
        List<Diarias> listDiarias = diariasRepository.buscarDiariasPorPeriodo(this.dataInicio, this.dataFim);

        List<DiariasData> data = listDiarias.stream()
                .map(diarias -> modelMapper.map(diarias, DiariasData.class))
                .collect(Collectors.toList());

        diariasResponse.setDiariasData(data);

        return diariasResponse;
    }

    private void prepararDatasDoPeriodo(Integer mes, Integer ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, 1); // Mês começa em 0 no Calendar (janeiro é 0)
        this.dataInicio = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataFim = calendar.getTime();
    }
}
