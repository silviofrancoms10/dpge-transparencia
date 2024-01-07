/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.sboot.autoconfigure.transparencia;

import br.def.ms.defensoria.transparencia.services.integration.PncpAuthProperties;
import br.def.ms.defensoria.transparencia.services.integration.SpfAuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasePropertiesConfig {
    @Bean
    @ConfigurationProperties(prefix = PncpAuthProperties.PNCP_PROPERTIES_PREFIX)
    public PncpAuthProperties pncpAuthProperties() {
        return new PncpAuthProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = SpfAuthProperties.SPF_PROPERTIES_PREFIX)
    public SpfAuthProperties spfAuthProperties() {
        return new SpfAuthProperties();
    }
}
