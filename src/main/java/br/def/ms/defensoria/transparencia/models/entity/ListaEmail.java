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

@Getter
@Setter
@ToString
@Entity
@Table(name = "VIEW_LISTA_EMAIL_GERAL")
public class ListaEmail {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String nome;
    private String emailFuncional;
    private String emailPessoal;
    @Enumerated(EnumType.STRING)
    private EnumTipoServidor tipoServidor;
}