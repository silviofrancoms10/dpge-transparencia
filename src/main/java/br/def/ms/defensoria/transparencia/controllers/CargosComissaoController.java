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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.comissao.CargosComissaoResponse;
import br.def.ms.defensoria.transparencia.services.CargosComissaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Cargos Comissão Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Cargos Comissão Controller", description = "Documentação da API Cargos Comissão Controller")
})
@RequestMapping(value = CargosComissaoController.BASE_URL)
public class CargosComissaoController {

    static final String BASE_URL = "/cargosComissao";
    private final CargosComissaoService cargosComissaoService;
    private final ModelMapper modelMapper;

    public CargosComissaoController(CargosComissaoService cargosComissaoService, ModelMapper modelMapper) {
        this.cargosComissaoService = cargosComissaoService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna todos cargos comissão")
    @GetMapping
    public CargosComissaoResponse listarCargosComissao() {
        return cargosComissaoService.listarCargosComissao();
    }

    @ApiOperation(value = "Retorna todos cargos comissão por período")
    @PostMapping("/periodo")
    public CargosComissaoResponse listarCargosComissaoPorPeriodo(@Valid @RequestBody CargosComissaoRequest request) {
        return cargosComissaoService.listarCargosComissaoPorPeriodo(request);
    }
}
