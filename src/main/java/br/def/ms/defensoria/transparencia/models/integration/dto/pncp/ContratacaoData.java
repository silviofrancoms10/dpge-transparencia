/*
 *   Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 *   Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 *   forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 *   or circumvent any protection mechanism; to sell, license, lease, rent,
 *   redistribute or make accessible to any third party, whether for profit or
 *   without charge.
 */

package br.def.ms.defensoria.transparencia.models.integration.dto.pncp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratacaoData {
    private Double valorTotalEstimado;
    private Double valorTotalHomologado;
    private String numeroCompra;
    private OrgaoSubRogadoData orgaoSubRogado;
    private UnidadeOrgaoData unidadeOrgao;
    private UnidadeSubRogadaData unidadeSubRogada;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataPublicacaoPncp;
    private Integer modalidadeId;
    private Boolean srp;
    private Integer anoCompra;
    private Integer sequencialCompra;
    private OrgaoData orgaoEntidade;
    private AmparoLegalData amparoLegal;
    private LocalDateTime dataAberturaProposta;
    private LocalDateTime dataEncerramentoProposta;
    private String informacaoComplementar;
    private String processo;
    private String objetoCompra;
    private String linkSistemaOrigem;
    private String justificativaPresencial;
    private Boolean existeResultado;
    private String numeroControlePNCP;
    private Integer orcamentoSigilosoCodigo;
    private String orcamentoSigilosoDescricao;
    private Integer situacaoCompraId;
    private String situacaoCompraNome;
    private Integer tipoInstrumentoConvocatorioCodigo;
    private String tipoInstrumentoConvocatorioNome;
    private Integer modoDisputaId;
    private String modoDisputaNome;
    private String usuarioNome;
    private String modalidadeNome;
}
