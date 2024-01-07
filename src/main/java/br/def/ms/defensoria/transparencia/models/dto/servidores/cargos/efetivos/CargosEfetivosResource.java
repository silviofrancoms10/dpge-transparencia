/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class CargosEfetivosResource implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String CARGOS_EFETIVOS_RESOURCE = "cargosEfetivosResource";

    @NotNull
    private Integer mes;
    @NotNull
    private Integer ano;
}
