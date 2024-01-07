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

import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.quadros.QuadroDefensoriaPublicaResponse;
import br.def.ms.defensoria.transparencia.services.QuadroDefensoriaPublicaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Quadro Defensoria Publica Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Quadro Defensoria Publica Controller", description = "Documentação da API Quadro Defensoria Publica Controller")
})
@RequestMapping(value = QuadroDefensoriaPublicaController.BASE_URL)
public class QuadroDefensoriaPublicaController {
    static final String BASE_URL = "/quadroDefensoriaPublica";
    private final QuadroDefensoriaPublicaService quadroDefensoriaPublicaService;
    private final ModelMapper modelMapper;

    public QuadroDefensoriaPublicaController(QuadroDefensoriaPublicaService quadroDefensoriaPublicaService, ModelMapper modelMapper) {
        this.quadroDefensoriaPublicaService = quadroDefensoriaPublicaService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna todo o quadro da defensoria publica")
    @GetMapping
    public QuadroDefensoriaPublicaResponse listarQuadroDefensoriaPublica() {
        return quadroDefensoriaPublicaService.listarQuadroDefensoriaPublica();
    }

    @ApiOperation(value = "Retorna todo o quadro da defensoria publica por periodo")
    @PostMapping("/periodo")
    public QuadroDefensoriaPublicaResponse listarQuadroDefensoriaPublicaPorPeriodo(@Valid @RequestBody QuadroDefensoriaPublicaRequest request) {
        return quadroDefensoriaPublicaService.listarQuadroDefensoriaPublicaPorPeriodo(request);
    }
}
