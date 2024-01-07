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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.defensor.SubsidioDefensorResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioDefensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = {"Subsídio Defensor Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Subsídio Defensor Controller", description = "Documentação da API Subsídio Defensor Controller")
})
@RequestMapping(value = SubsidioDefensorController.BASE_URL)
public class SubsidioDefensorController {
    static final String BASE_URL = "/subsidio/defensor";
    private final SubsidioDefensorService subsidioDefensorService;
    private final ModelMapper modelMapper;

    public SubsidioDefensorController(SubsidioDefensorService subsidioDefensorService, ModelMapper modelMapper) {
        this.subsidioDefensorService = subsidioDefensorService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Retorna os anos disponíveis para consulta dos subsídios de defensor")
    @GetMapping
    public List<Integer> listarAno() {
        return subsidioDefensorService.listarAno();
    }

    @ApiOperation(value = "Retorna os subsídios de defensor por ano")
    @GetMapping("/{ano}")
    public SubsidioDefensorResponse listarSubsidioDefensor(@Valid @PathVariable Integer ano) {
        return subsidioDefensorService.listarSubsidioDefensor(ano);
    }
}
