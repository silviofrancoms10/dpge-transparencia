/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *  Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *  forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *  or circumvent any protection mechanism; to sell, license, lease, rent,
 *  redistribute or make accessible to any third party, whether for profit or
 *  without charge.
 */

package br.def.ms.defensoria.transparencia.models.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "VIEW_DIARIAS_PAGA_TRANSPARENCIA")
public class Diarias implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String matricula;
    private Date dataPedido;
    private Integer codBeneficiario;
    private String nomeBeneficiario;
    private String cargo;
    private String nomeMunicipioOrigem;
    private String nomeMunicipioDestino;
    @Temporal(TemporalType.DATE)
    private Date dataHoraChegada;
    @Temporal(TemporalType.DATE)
    private Date dataHoraSaida;
    private String justificativa;
    private Double qtdeDiaria;
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    private Double valorComplementar;
    private Double valorDiaria;
}
