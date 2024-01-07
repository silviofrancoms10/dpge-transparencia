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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResponse;
import br.def.ms.defensoria.transparencia.models.entity.CargosEfetivos;
import br.def.ms.defensoria.transparencia.repositories.servidores.CargosEfetivosRepository;
import org.modelmapper.ModelMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CargosEfetivosService {

    private final CargosEfetivosRepository cargosEfetivosRepository;
    private final ModelMapper modelMapper;
    private Date dataInicio, dataFim;

    public CargosEfetivosService(CargosEfetivosRepository cargosEfetivosRepository, ModelMapper modelMapper) {
        this.cargosEfetivosRepository = cargosEfetivosRepository;
        this.modelMapper = modelMapper;
    }

    public CargosEfetivosResponse listarCargosEfetivos() {
        CargosEfetivosResponse cargosEfetivosResponse = new CargosEfetivosResponse();
        List<CargosEfetivos> listCargosEfetivos = cargosEfetivosRepository.findAll();

        List<CargosEfetivosData> data = listCargosEfetivos.stream()
                .map(cargosEfetivos -> modelMapper.map(cargosEfetivos, CargosEfetivosData.class))
                .collect(Collectors.toList());

        cargosEfetivosResponse.setCargosEfetivosData(data);

        return cargosEfetivosResponse;
    }

    public CargosEfetivosResponse listarCargosEfetivosPorPeriodo(CargosEfetivosRequest request) {
        prepararDatasDoPeriodo(request.getCargosEfetivosResource().getMes(), request.getCargosEfetivosResource().getAno());

        CargosEfetivosResponse cargosEfetivosResponse = new CargosEfetivosResponse();
        List<CargosEfetivos> listCargosEfetivos = cargosEfetivosRepository.buscarCargosEfetivosPorPeriodo(this.dataInicio, this.dataFim);

        List<CargosEfetivosData> data = listCargosEfetivos.stream()
                .map(cargosEfetivos -> modelMapper.map(cargosEfetivos, CargosEfetivosData.class))
                .collect(Collectors.toList());

        cargosEfetivosResponse.setCargosEfetivosData(data);

        return cargosEfetivosResponse;
    }

    private void prepararDatasDoPeriodo(Integer mes, Integer ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, 1); // Mês começa em 0 no Calendar (janeiro é 0)
        this.dataInicio = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataFim = calendar.getTime();
    }
}
