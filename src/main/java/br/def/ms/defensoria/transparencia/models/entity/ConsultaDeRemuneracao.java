/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.entity;

import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "VIEW_CONSULTA_REMUNERACAO_TRANSPARENCIA")
public class ConsultaDeRemuneracao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ConsultaRemuneracaoId id;
    private String nomeBeneficiario;
    private Integer codBeneficiario;
    @Enumerated(EnumType.STRING)
    private EnumTipoServidor categoriaServidor;
    private String nomeCargo;
    private String nomeCargoComissao;
    private String idGrupo;
    private String idSubGrupo;
    private Integer mes;
    private Integer ano;
    private String complementar;
    private String comarca;
    private String lotacao;
    private Double valor;
}
