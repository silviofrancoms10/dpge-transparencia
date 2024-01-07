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

import br.def.ms.defensoria.transparencia.models.integration.dto.spf.LoginData;
import br.def.ms.defensoria.transparencia.services.integration.SpfIntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"SPF Integration Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Integration with SPF Controller", description = "Documentação da API de Integração com SPF")
})
@RequestMapping(value = SpfIntegrationController.BASE_URL)
public class SpfIntegrationController {
    static final String BASE_URL = "/spf";
    private final SpfIntegrationService spfIntegrationService;

    public SpfIntegrationController(SpfIntegrationService spfIntegrationService) {
        this.spfIntegrationService = spfIntegrationService;
    }

    @ApiOperation(value = "Faz o login e retorna o token de autorização")
    @PostMapping(value = "/login")
    public LoginData login() {
        return spfIntegrationService.loginMethod();
    }
}
