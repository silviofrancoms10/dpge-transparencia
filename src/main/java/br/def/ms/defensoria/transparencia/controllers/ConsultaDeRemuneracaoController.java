/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.controllers;

import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao.ConsultaDeRemuneracaoResponse;
import br.def.ms.defensoria.transparencia.services.ConsultaDeRemuneracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = {"Consulta de Remuneração Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Consulta de Remuneração Controller", description = "Documentação da API Consulta de Remuneração Controller")
})
@RequestMapping(value = ConsultaDeRemuneracaoController.BASE_URL)
public class ConsultaDeRemuneracaoController {
    static final String BASE_URL = "/consultaRemuneracao";
    private final ConsultaDeRemuneracaoService consultaDeRemuneracaoService;
    private final ModelMapper modelMapper;

    public ConsultaDeRemuneracaoController(ConsultaDeRemuneracaoService consultaDeRemuneracaoService, ModelMapper modelMapper) {
        this.consultaDeRemuneracaoService = consultaDeRemuneracaoService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna a consulta de remuneração por mês e ano")
    @PostMapping
    public ConsultaDeRemuneracaoResponse listarConsultaDeRemuneracao (@Valid @RequestBody ConsultaDeRemuneracaoRequest consultaDeRemuneracaoRequest) {
        return consultaDeRemuneracaoService.listarConsultaDeRemuneracaoPorFiltros(consultaDeRemuneracaoRequest);
    }
}
