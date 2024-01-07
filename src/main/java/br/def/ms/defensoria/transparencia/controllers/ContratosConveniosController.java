/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.controllers;

import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosRequest;
import br.def.ms.defensoria.transparencia.models.dto.convenios.ContratosConveniosResponse;
import br.def.ms.defensoria.transparencia.services.ContratosConveniosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Contratos Convenios Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Contratos Convenios Controller", description = "Documentação da API Contratos Convenios Controller")
})
@RequestMapping(value = ContratosConveniosController.BASE_URL)
public class ContratosConveniosController {
    static final String BASE_URL = "/contratosConvenios";
    private final ContratosConveniosService contratosConveniosService;
    private final ModelMapper modelMapper;

    public ContratosConveniosController(ContratosConveniosService contratosConveniosService, ModelMapper modelMapper) {
        this.contratosConveniosService = contratosConveniosService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna processos de contratos e convenios por parametros")
    @PostMapping
    public ContratosConveniosResponse listarProcessosPorParametros(@Valid @RequestBody ContratosConveniosRequest request) {
        return contratosConveniosService.listarProcessosPorParametros(request);
    }

    @ApiOperation(value = "Retorna todos processos de contratos e convenios por periodo")
    @GetMapping("/{ano}")
    public ContratosConveniosResponse listarProcessosPorPeriodo(@PathVariable Integer ano) {
        return contratosConveniosService.listarProcessosPorPeriodo(ano);
    }
}
