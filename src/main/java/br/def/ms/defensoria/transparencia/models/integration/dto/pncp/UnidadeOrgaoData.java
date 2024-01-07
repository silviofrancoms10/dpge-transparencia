/*
 *   Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *   Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *   forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *   or circumvent any protection mechanism; to sell, license, lease, rent,
 *   redistribute or make accessible to any third party, whether for profit or
 *   without charge.
 */

package br.def.ms.defensoria.transparencia.models.integration.dto.pncp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnidadeOrgaoData {
    private String ufNome;
    private String codigoUnidade;
    private String nomeUnidade;
    private String ufSigla;
    private String municipioNome;
    private String codigoIbge;
}
