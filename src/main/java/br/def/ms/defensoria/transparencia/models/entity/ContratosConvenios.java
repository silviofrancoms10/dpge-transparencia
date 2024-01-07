/*
 *  Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
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
@Table(name = "VIEW_CONTRATOS_CONVENIOS")
public class ContratosConvenios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;
    private String identificacaoUnica;
    private String nomeContratada;
    private String modalidade;
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicial;
    @Temporal(TemporalType.DATE)
    private Date vigenciaFinal;
    private String objeto;
    private Integer encerrado;
    private String publicadoNoPortal;
    @Column(name = "processo_codigo")
    private Long processoCodigo;
    @Column(name = "nome_anexo")
    private String nomeAnexo;
    @Column(name = "descricao_anexo", columnDefinition = "varchar(max)")
    private String descricaoAnexo;
    @Column(name = "publicadoNoPortal_anexo")
    private String publicadoNoPortalAnexo;
    private String caminho;
}
