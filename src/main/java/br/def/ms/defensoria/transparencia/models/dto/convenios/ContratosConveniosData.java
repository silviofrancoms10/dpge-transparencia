/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.convenios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ContratosConveniosData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String identificacaoUnica;
    private String nomeContratada;
    private String modalidade;
    private Date vigenciaInicial;
    private Date vigenciaFinal;
    private String objeto;
    private Integer encerrado;
    private Map<String, String> mapaDeAnexos;
}
