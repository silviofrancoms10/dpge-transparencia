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

import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResponse;
import br.def.ms.defensoria.transparencia.services.ServidoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Servidores Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Servidores Controller", description = "Documentação da API Servidores Controller")
})
@RequestMapping(value = ServidoresController.BASE_URL)
public class ServidoresController {
    static final String BASE_URL = "/servidores";

    private final ServidoresService servidoresService;

    public ServidoresController(ServidoresService servidoresService) {
        this.servidoresService = servidoresService;
    }

    @ApiOperation(value = "Retorna a lista de email por tipo de servidor")
    @PostMapping("/listaEmails")
    public ListaEmailResponse retornaListaDeEmailsPorTipoServidor(@Valid @RequestBody ListaEmailRequest listaEmailRequest) {
        return servidoresService.obterListaEmailPorTipoServidor(listaEmailRequest);
    }
}
