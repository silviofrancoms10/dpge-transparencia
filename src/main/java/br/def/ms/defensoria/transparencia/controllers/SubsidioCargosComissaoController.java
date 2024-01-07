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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.cargosComissao.SubsidioCargosComissaoResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioCargosComissaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = {"Subsídio Cargos Comissao Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Subsídio Cargos Comissao Controller", description = "Documentação da API Subsídio Cargos Comissao Controller")
})
@RequestMapping(value = SubsidioCargosComissaoController.BASE_URL)
public class SubsidioCargosComissaoController {
    static final String BASE_URL = "/subsidio/cargosComissao";
    private final SubsidioCargosComissaoService subsidioCargosComissaoService;
    private final ModelMapper modelMapper;

    public SubsidioCargosComissaoController(SubsidioCargosComissaoService subsidioCargosComissaoService, ModelMapper modelMapper) {
        this.subsidioCargosComissaoService = subsidioCargosComissaoService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna os anos disponíveis para consulta dos subsídios de cargos comissao")
    @GetMapping
    public List<Integer> listarAno() {
        return subsidioCargosComissaoService.listarAno();
    }

    @ApiOperation(value = "Retorna os subsídios de cargos comissao por ano")
    @GetMapping("/{ano}")
    public SubsidioCargosComissaoResponse listarSubsidioCargosComissao(@Valid @PathVariable Integer ano) {
        return subsidioCargosComissaoService.listarSubsidioCargosComissao(ano);
    }
}
