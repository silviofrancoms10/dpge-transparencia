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

import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResponse;
import br.def.ms.defensoria.transparencia.models.entity.ConsultaDeRemuneracao;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import br.def.ms.defensoria.transparencia.repositories.servidores.ConsultaDeRemuneracaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultaDeRemuneracaoService {
    private final ConsultaDeRemuneracaoRepository consultaDeRemuneracaoRepository;
    private final ModelMapper modelMapper;

    public ConsultaDeRemuneracaoService(ConsultaDeRemuneracaoRepository consultaDeRemuneracaoRepository, ModelMapper modelMapper) {
        this.consultaDeRemuneracaoRepository = consultaDeRemuneracaoRepository;
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "consultaRemuneracao")
    public ConsultaDeRemuneracaoResponse listarConsultaDeRemuneracaoPorFiltros(ConsultaDeRemuneracaoRequest consultaDeRemuneracaoRequest) {
        Integer mes = consultaDeRemuneracaoRequest.getConsultaDeRemuneracaoResource().getMes();
        Integer ano = consultaDeRemuneracaoRequest.getConsultaDeRemuneracaoResource().getAno();
        EnumTipoServidor categoriaServidor = consultaDeRemuneracaoRequest.getConsultaDeRemuneracaoResource().getCategoriaServidor();

        ConsultaDeRemuneracaoResponse consultaDeRemuneracaoResponse = new ConsultaDeRemuneracaoResponse();
        List<ConsultaDeRemuneracao> listConsultaDeRemuneracao = consultaDeRemuneracaoRepository.buscarConsultaDeRemuneracaoPorFiltros(mes, ano, categoriaServidor);
        if (!CollectionUtils.isEmpty(listConsultaDeRemuneracao)) {
            List<ConsultaDeRemuneracaoData> remuneracaoDataList = construirConsultaDeRemuneracaoData(listConsultaDeRemuneracao);
            consultaDeRemuneracaoResponse.setConsultaDeRemuneracaoData(remuneracaoDataList);
        }

        return consultaDeRemuneracaoResponse;
    }

    public List<ConsultaDeRemuneracaoData> construirConsultaDeRemuneracaoData(List<ConsultaDeRemuneracao> listaConsultaRemuneracao) {
        List<ConsultaDeRemuneracaoData> remuneracaoDataList = new ArrayList<>();

        // Agrupando por servidores
        Map<Long, List<ConsultaDeRemuneracao>> mapServidores;
        mapServidores = listaConsultaRemuneracao.stream().collect(Collectors.groupingBy(rem -> rem.getId().getMatricula()));

        mapServidores.forEach((k, remuneracao) -> {
            ConsultaDeRemuneracao objetoReferencia = remuneracao.stream().filter(rem -> rem.getId().getMatricula().equals(k)).findFirst().get();

            // Configurar objeto de response data
            ConsultaDeRemuneracaoData consultaDeRemuneracaoData = new ConsultaDeRemuneracaoData();
            consultaDeRemuneracaoData.setNomeBeneficiario(objetoReferencia.getNomeBeneficiario());
            consultaDeRemuneracaoData.setMatricula(objetoReferencia.getId().getMatricula());
            consultaDeRemuneracaoData.setNomeCargo(objetoReferencia.getNomeCargo());
            consultaDeRemuneracaoData.setComarca(objetoReferencia.getComarca());
            consultaDeRemuneracaoData.setLotacao(objetoReferencia.getLotacao());

            Map<String, Double> mapaDeValores = new HashMap<>();
            remuneracao.forEach(valores -> mapaDeValores.put(valores.getId().getSubGrupoRelatorio(), valores.getValor()));
            consultaDeRemuneracaoData.setMapaDeValores(mapaDeValores);

            remuneracaoDataList.add(consultaDeRemuneracaoData);
        });

        // Ordena a lista por nome e retorna a nova lista
        return remuneracaoDataList.stream().sorted(Comparator.comparing(ConsultaDeRemuneracaoData::getNomeBeneficiario)).collect(Collectors.toList());
    }
}
