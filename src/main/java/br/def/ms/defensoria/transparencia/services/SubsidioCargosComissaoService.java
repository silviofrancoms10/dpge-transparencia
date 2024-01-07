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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.cargosComissao.SubsidioCargosComissaoData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.cargosComissao.SubsidioCargosComissaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.SubsidioCargosComissao;
import br.def.ms.defensoria.transparencia.repositories.servidores.SubsidioCargosComissaoRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SubsidioCargosComissaoService {
    private final SubsidioCargosComissaoRepository subsidioCargosComissaoRepository;
    private final ModelMapper modelMapper;

    public SubsidioCargosComissaoService(SubsidioCargosComissaoRepository subsidioCargosComissaoRepository, ModelMapper modelMapper) {
        this.subsidioCargosComissaoRepository = subsidioCargosComissaoRepository;
        this.modelMapper = modelMapper;
    }

    public SubsidioCargosComissaoResponse listarSubsidioCargosComissao(Integer ano) {
        SubsidioCargosComissaoResponse subsidioCargosComissaoResponse = new SubsidioCargosComissaoResponse();
        List<SubsidioCargosComissao> listSubsidioCargosComissao = subsidioCargosComissaoRepository.buscarSubsidioCargosComissaoPorAno(ano);

        List<SubsidioCargosComissaoData> data = listSubsidioCargosComissao.stream()
                .map(subsidioCargosComissao -> modelMapper.map(subsidioCargosComissao, SubsidioCargosComissaoData.class))
                .collect(Collectors.toList());

        subsidioCargosComissaoResponse.setSubsidioCargosComissaoData(data);

        return subsidioCargosComissaoResponse;
    }

    public List<Integer> listarAno() {
        return subsidioCargosComissaoRepository.buscarListaAno();
    }
}
