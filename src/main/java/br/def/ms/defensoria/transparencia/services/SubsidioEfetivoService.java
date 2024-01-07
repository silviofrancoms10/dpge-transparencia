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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.efetivo.SubsidioEfetivoData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.efetivo.SubsidioEfetivoResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioEfetivo;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioEfetivoRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SubsidioEfetivoService {
    private final SubsidioEfetivoRepository subsidioEfetivoRepository;
    private final ModelMapper modelMapper;

    public SubsidioEfetivoService(SubsidioEfetivoRepository subsidioEfetivoRepository, ModelMapper modelMapper) {
        this.subsidioEfetivoRepository = subsidioEfetivoRepository;
        this.modelMapper = modelMapper;
    }

    public SubsidioEfetivoResponse listarSubsidioEfetivo(Integer ano) {
        SubsidioEfetivoResponse subsidioEfetivoResponse = new SubsidioEfetivoResponse();
        List<SubsidioEfetivo> listSubsidioEfetivo = subsidioEfetivoRepository.buscarSubsidioEfetivoPorAno(ano);

        List<SubsidioEfetivoData> data = listSubsidioEfetivo.stream()
                .map(subsidioEfetivo -> modelMapper.map(subsidioEfetivo, SubsidioEfetivoData.class))
                .collect(Collectors.toList());

        subsidioEfetivoResponse.setSubsidioEfetivoData(data);

        return subsidioEfetivoResponse;
    }

    public List<Integer> listarAno() {
        return subsidioEfetivoRepository.buscarListaAno();
    }
}
