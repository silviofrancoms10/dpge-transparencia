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
@Table(name = "VIEW_QUANTITATIVO_DEFENSOR")
public class QuadroDefensoriaPublica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer idSimbolo;
    private String carreira;
    private String cargo;
    private String classe;
    private String simbolo;
    private Integer preenchidas;
    private Integer previstas;
    private Integer idCarreira;
    private Integer idCargo;
    private Integer idClasse;
    private Integer idDadosFuncionais;
    private String matricula;
    private Integer idBeneficiario;
    private String nomeBeneficiario;
    @Temporal(TemporalType.DATE)
    private Date dataExercicio;
    @Temporal(TemporalType.DATE)
    private Date dataDemissaoExoneracao;
}
