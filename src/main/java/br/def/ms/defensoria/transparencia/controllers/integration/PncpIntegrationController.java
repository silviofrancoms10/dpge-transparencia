/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.controllers.integration;

import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.ContratacaoData;
import br.def.ms.defensoria.transparencia.models.integration.dto.pncp.UnidadeData;
import br.def.ms.defensoria.transparencia.services.integration.PncpIntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"PNCP Integration Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Integration with PNCP Controller", description = "Documentação da API de Integração com PNCP")
})
@RequestMapping(value = PncpIntegrationController.BASE_URL)
public class PncpIntegrationController {
    static final String BASE_URL = "/pncp";
    private final PncpIntegrationService pncpIntegrationService;

    public PncpIntegrationController(PncpIntegrationService pncpIntegrationService) {
        this.pncpIntegrationService = pncpIntegrationService;
    }

    @ApiOperation(value = "Faz o login e retorna o token de autorização")
    @PostMapping(value = "/login")
    public HttpHeaders login() {
        return pncpIntegrationService.loginMethod();
    }

    @ApiOperation(value = "Retorna a consulta de unidade")
    @GetMapping(value = "/unidade")
    public UnidadeData consultUnit() {
        return pncpIntegrationService.consultUnitMethod();
    }

    @ApiOperation(value = "Retorna a consulta de contratação")
    @GetMapping(value = "/contratacao/{ano}/{sequencial}")
    public ContratacaoData consultContratation(@PathVariable("ano") Integer ano, @PathVariable("sequencial") Integer sequencial) {
        return pncpIntegrationService.consultContratationMethod(ano, sequencial);
    }
}
