/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.servidores.remuneracao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ConsultaDeRemuneracaoData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long matricula;
    private String nomeBeneficiario;
    private String nomeCargo;
    private String comarca;
    private String lotacao;
    private Map<String, Double> mapaDeValores;
}
