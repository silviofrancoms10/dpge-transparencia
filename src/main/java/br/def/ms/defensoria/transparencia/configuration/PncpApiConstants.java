/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.configuration;

public class PncpApiConstants {
    // Main paths
    public static final String PNCP_LOGIN_V1 = "/v1/usuarios/login";
    public static final String PNCP_CONSULT_AGENCY_V1 = "/v1/orgaos";

    // Sub paths
    public static final String PNCP_CONSULT_UNIT_V1 = "/unidades";
    public static final String PNCP_CONSULT_CONTRATATION_V1 = "/compras";
}
