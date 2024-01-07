/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.repositories.servidores;

import br.def.ms.defensoria.transparencia.models.entity.SubsidioCargosComissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubsidioCargosComissaoRepository extends JpaRepository<SubsidioCargosComissao, Long> {

    @Transactional(readOnly = true)
    @Query(value = " SELECT tbCargosComissao FROM SubsidioCargosComissao tbCargosComissao " +
            "WHERE DATEPART(YEAR, tbCargosComissao.dataCompetencia) = :anoParametro  " +
            "ORDER BY tbCargosComissao.descricao ASC")
    List<SubsidioCargosComissao> buscarSubsidioCargosComissaoPorAno(@Param("anoParametro") Integer anoParametro);

    @Transactional(readOnly = true)
    @Query(value = " SELECT DATEPART(YEAR, tabela.dataCompetencia) " +
            " FROM BD_DEFENSORIA_GESTAOPESSOAL.dbo.VIEW_TABELA_SALARIAL_COMISSIONADOS tabela " +
            " GROUP BY DATEPART(YEAR, tabela.dataCompetencia)", nativeQuery = true)
    List<Integer> buscarListaAno();
}
