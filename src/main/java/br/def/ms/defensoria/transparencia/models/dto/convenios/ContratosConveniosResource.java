/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.convenios;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ContratosConveniosResource implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String CONTRATOS_CONVENIOS_RESOURCE = "contratosConveniosResource";

    private String identificacaoUnica;
    private String nomeContratada;
    private String modalidade;
    private Integer encerrado;
}
