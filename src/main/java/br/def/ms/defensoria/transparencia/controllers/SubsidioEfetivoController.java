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

import br.def.ms.defensoria.transparencia.models.dto.servidores.subsidios.efetivo.SubsidioEfetivoResponse;
import br.def.ms.defensoria.transparencia.services.SubsidioEfetivoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = {"Subsidio Efetivo Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Subsidio Efetivo Controller", description = "Documentação da API Subsidio Efetivo Controller")
})
@RequestMapping(value = SubsidioEfetivoController.BASE_URL)
public class SubsidioEfetivoController {
    static final String BASE_URL = "/subsidio/efetivo";
    private final SubsidioEfetivoService subsidioEfetivoService;
    private final ModelMapper modelMapper;

    public SubsidioEfetivoController(SubsidioEfetivoService subsidioEfetivoService, ModelMapper modelMapper) {
        this.subsidioEfetivoService = subsidioEfetivoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Integer> listarAno() {
        return subsidioEfetivoService.listarAno();
    }

    @ApiOperation(value = "Retorna todos subsidios de efetivo")
    @GetMapping("/{ano}")
    public SubsidioEfetivoResponse listarSubsidioEfetivo(@Valid @PathVariable Integer ano) {
        return subsidioEfetivoService.listarSubsidioEfetivo(ano);
    }
}
