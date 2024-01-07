/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.services;

import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosData;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosRequest;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResponse;
import br.def.ms.defensoria.transparencia.models.entity.ContratosConvenios;
import br.def.ms.defensoria.transparencia.repositories.convenios.ContratosConveniosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ContratosConveniosService {

    private final ContratosConveniosRepository contratosConveniosRepository;
    private final ModelMapper modelMapper;
    private Date dataInicio, dataFim;

    public ContratosConveniosService(ContratosConveniosRepository contratosConveniosRepository, ModelMapper modelMapper) {
        this.contratosConveniosRepository = contratosConveniosRepository;
        this.modelMapper = modelMapper;
    }

    public ContratosConveniosResponse listarProcessosPorPeriodo(Integer ano) {
        prepararDatasDoPeriodo(ano);

        ContratosConveniosResponse contratosConveniosResponse = new ContratosConveniosResponse();
        List<ContratosConvenios> listContratosConvenios = contratosConveniosRepository.buscarContratosConveniosPorAno(this.dataInicio, this.dataFim);

        if (!CollectionUtils.isEmpty(listContratosConvenios)) {
            List<ContratosConveniosData> contratosConveniosDataList = construirContratosConveniosData(listContratosConvenios);
            contratosConveniosResponse.setContratosConveniosData(contratosConveniosDataList);
        }
        return contratosConveniosResponse;
    }

    public ContratosConveniosResponse listarProcessosPorParametros(ContratosConveniosRequest request) {
        ContratosConveniosResponse contratosConveniosResponse = new ContratosConveniosResponse();
        List<ContratosConvenios> listContratosConvenios = contratosConveniosRepository.buscarContratosConveniosPorParametros(
                StringUtils.hasText(request.getContratosConveniosResource().getIdentificacaoUnica()) ? request.getContratosConveniosResource().getIdentificacaoUnica() : "",
                StringUtils.hasText(request.getContratosConveniosResource().getNomeContratada()) ? request.getContratosConveniosResource().getNomeContratada() : "",
                StringUtils.hasText(request.getContratosConveniosResource().getModalidade()) ? request.getContratosConveniosResource().getModalidade() : "",
                request.getContratosConveniosResource().getEncerrado() != null ? request.getContratosConveniosResource().getEncerrado() : 0
        );

        if (!CollectionUtils.isEmpty(listContratosConvenios)) {
            List<ContratosConveniosData> contratosConveniosDataList = construirContratosConveniosData(listContratosConvenios);
            contratosConveniosResponse.setContratosConveniosData(contratosConveniosDataList);
        }
        return contratosConveniosResponse;
    }

    public List<ContratosConveniosData> construirContratosConveniosData(List<ContratosConvenios> listaContratosConvenios) {
        List<ContratosConveniosData> contratosConveniosDataList = new ArrayList<>();

        //Agrupando por processos
        Map<String, List<ContratosConvenios>> mapProcessos;
        mapProcessos = listaContratosConvenios.stream().collect(Collectors.groupingBy(ContratosConvenios::getIdentificacaoUnica));

        mapProcessos.forEach((k, anexo) -> {
            ContratosConvenios objetoReferencia = anexo.stream().filter(anx -> anx.getIdentificacaoUnica().equals(k)).findFirst().get();

            //Configurar objeto de response data
            ContratosConveniosData contratosConveniosData = new ContratosConveniosData();
            contratosConveniosData.setIdentificacaoUnica(objetoReferencia.getIdentificacaoUnica());
            contratosConveniosData.setNomeContratada(objetoReferencia.getNomeContratada());
            contratosConveniosData.setModalidade(objetoReferencia.getModalidade());
            contratosConveniosData.setVigenciaInicial(objetoReferencia.getVigenciaInicial());
            contratosConveniosData.setVigenciaFinal(objetoReferencia.getVigenciaFinal());
            contratosConveniosData.setObjeto(objetoReferencia.getObjeto());
            contratosConveniosData.setEncerrado(objetoReferencia.getEncerrado());

            Map<String, String> mapaDeAnexos = new HashMap<>();
            anexo.forEach(valores -> mapaDeAnexos.put(valores.getDescricaoAnexo(), valores.getCaminho()));
            contratosConveniosData.setMapaDeAnexos(mapaDeAnexos);

            contratosConveniosDataList.add(contratosConveniosData);
        });
        return contratosConveniosDataList.stream().sorted(Comparator.comparing(ContratosConveniosData::getIdentificacaoUnica)).collect(Collectors.toList());
    }

    private void prepararDatasDoPeriodo(Integer ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, Calendar.JANUARY, 1);
        this.dataInicio = calendar.getTime();

        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataFim = calendar.getTime();
    }
}
