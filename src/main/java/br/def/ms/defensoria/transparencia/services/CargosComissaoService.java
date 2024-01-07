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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.CargosComissao;
import br.def.ms.defensoria.transparencia.repositories.servidores.CargosComissaoRepository;
import org.modelmapper.ModelMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CargosComissaoService {

    private final CargosComissaoRepository cargosComissaoRepository;
    private final ModelMapper modelMapper;
    private Date dataInicio, dataFim;

    public CargosComissaoService(CargosComissaoRepository cargosComissaoRepository, ModelMapper modelMapper) {
        this.cargosComissaoRepository = cargosComissaoRepository;
        this.modelMapper = modelMapper;
    }

    public CargosComissaoResponse listarCargosComissao() {
        CargosComissaoResponse cargosComissaoResponse = new CargosComissaoResponse();
        List<CargosComissao> listCargosComissao = cargosComissaoRepository.findAll();

        List<CargosComissaoData> data = listCargosComissao.stream()
                .map(cargosComissao -> modelMapper.map(cargosComissao, CargosComissaoData.class))
                .collect(Collectors.toList());

        cargosComissaoResponse.setCargosComissaoData(data);

        return cargosComissaoResponse;
    }

    public CargosComissaoResponse listarCargosComissaoPorPeriodo(CargosComissaoRequest request) {
        prepararDatasDoPeriodo(request.getCargosComissaoResource().getMes(), request.getCargosComissaoResource().getAno());

        CargosComissaoResponse cargosComissaoResponse = new CargosComissaoResponse();
        List<CargosComissao> listCargosComissao = cargosComissaoRepository.buscarCargosComissaoPorPeriodo(this.dataInicio, this.dataFim);

        List<CargosComissaoData> data = listCargosComissao.stream()
                .map(cargosComissao -> modelMapper.map(cargosComissao, CargosComissaoData.class))
                .collect(Collectors.toList());

        cargosComissaoResponse.setCargosComissaoData(data);

        return cargosComissaoResponse;
    }

    private void prepararDatasDoPeriodo(Integer mes, Integer ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, 1); // Mês começa em 0 no Calendar (janeiro é 0)
        this.dataInicio = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataFim = calendar.getTime();
    }
}
