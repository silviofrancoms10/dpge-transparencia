/*
 *   Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *   Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *   forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *   or circumvent any protection mechanism; to sell, license, lease, rent,
 *   redistribute or make accessible to any third party, whether for profit or
 *   without charge.
 */

package br.def.ms.defensoria.transparencia.services.integration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpfAuthProperties {
    public static final String SPF_PROPERTIES_PREFIX = "spf";

    private String usuario;
    private String senha;
    private String baseUrl;
}
