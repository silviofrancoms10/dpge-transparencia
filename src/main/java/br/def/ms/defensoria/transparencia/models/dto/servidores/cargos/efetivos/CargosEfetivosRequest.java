/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.models.dto.servidores.cargos.efetivos;

import br.def.ms.defensoria.transparencia.models.dto.ApiRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class CargosEfetivosRequest extends ApiRequest {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "cargosEfetivosRequest";
    public static final String RESOURCE_FIELDS_PREFIX = "cargosEfetivosRequest.";

    @Valid
    @NotNull
    @JsonProperty(CargosEfetivosResource.CARGOS_EFETIVOS_RESOURCE)
    private CargosEfetivosResource cargosEfetivosResource;
}