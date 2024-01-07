/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.controllers;

import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.diarias.DiariasResponse;
import br.def.ms.defensoria.transparencia.services.DiariasService;
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
@Api(tags = {"Diarias Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Diarias Controller", description = "Documentação da API Diarias Controller")
})
@RequestMapping(value = DiariasController.BASE_URL)
public class DiariasController {
    static final String BASE_URL = "/diarias";
    private final DiariasService diariasService;
    private final ModelMapper modelMapper;

    public DiariasController(DiariasService diariasService, ModelMapper modelMapper) {
        this.diariasService = diariasService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna todas diarias pagas por periodo")
    @PostMapping("/periodo")
    public DiariasResponse listarDiariasPorPeriodo(@Valid @RequestBody DiariasRequest request){
        return diariasService.listarDiariasPorPeriodo(request);
    }
}
