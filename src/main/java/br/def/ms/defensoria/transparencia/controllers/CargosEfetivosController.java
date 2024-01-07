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

import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos.CargosEfetivosResponse;
import br.def.ms.defensoria.transparencia.services.CargosEfetivosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Cargos Efetivos Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Cargos Efetivos Controller", description = "Documentação da API Cargos Efetivos Controller")
})
@RequestMapping(value = CargosEfetivosController.BASE_URL)
public class CargosEfetivosController {
    static final String BASE_URL = "/cargosEfetivos";
    private final CargosEfetivosService cargosEfetivosService;
    private final ModelMapper modelMapper;

    public CargosEfetivosController(CargosEfetivosService cargosEfetivosService, ModelMapper modelMapper) {
        this.cargosEfetivosService = cargosEfetivosService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna todos cargos efetivos")
    @GetMapping
    public CargosEfetivosResponse listarCargosEfetivos() {
        return cargosEfetivosService.listarCargosEfetivos();
    }

    @ApiOperation(value = "Retorna todos cargos efetivos por período")
    @PostMapping("/periodo")
    public CargosEfetivosResponse listarCargosEfetivosPorPeriodo(@Valid @RequestBody CargosEfetivosRequest request) {
        return cargosEfetivosService.listarCargosEfetivosPorPeriodo(request);
    }
}
