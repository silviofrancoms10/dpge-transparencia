/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Todas requests devem extender essa classe, pois nela teremos informações comuns para todas as requests
 *
 * @author rgsilva
 */
@Data
public class ApiRequest implements Serializable {
    private static final long serialVersionUID = 1L;
}
