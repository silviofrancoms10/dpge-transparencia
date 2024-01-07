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

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BasePropertiesConfig.class, ServiceConfig.class})
public class TransparenciaAutoConfiguration {

}
