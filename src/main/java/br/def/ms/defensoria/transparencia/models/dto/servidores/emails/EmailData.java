/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.servidores.emails;

import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String emailFuncional;
    private String emailPessoal;
    private EnumTipoServidor tipoServidor;
}
