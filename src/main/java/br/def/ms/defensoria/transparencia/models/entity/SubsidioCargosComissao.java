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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "VIEW_TABELA_SALARIAL_COMISSIONADOS")
public class SubsidioCargosComissao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String descricao;
    private Date dataCompetencia;
    private Integer valorReajuste;
    private Integer percentual;
}
