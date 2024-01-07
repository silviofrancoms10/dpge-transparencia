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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.defensor.SubsidioDefensorData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.defensor.SubsidioDefensorResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioDefensor;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioDefensorRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SubsidioDefensorService {

    private final SubsidioDefensorRepository subsidioDefensorRepository;
    private final ModelMapper modelMapper;

    public SubsidioDefensorService(SubsidioDefensorRepository subsidioDefensorRepository, ModelMapper modelMapper) {
        this.subsidioDefensorRepository = subsidioDefensorRepository;
        this.modelMapper = modelMapper;
    }

    public SubsidioDefensorResponse listarSubsidioDefensor(Integer ano) {
        SubsidioDefensorResponse subsidioDefensorResponse = new SubsidioDefensorResponse();
        List<SubsidioDefensor> listSubsidioDefensor = subsidioDefensorRepository.buscarSubsidioDefensorPorAno(ano);

        List<SubsidioDefensorData> data = listSubsidioDefensor.stream()
                .map(subsidioDefensor -> modelMapper.map(subsidioDefensor, SubsidioDefensorData.class))
                .collect(Collectors.toList());

        subsidioDefensorResponse.setSubsidioDefensorData(data);

        return subsidioDefensorResponse;
    }

    public List<Integer> listarAno() {
        return subsidioDefensorRepository.buscarListaAno();
    }
}
