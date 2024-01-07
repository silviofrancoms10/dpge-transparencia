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

import br.def.ms.defensoria.transparencia.models.entity.CargosComissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CargosComissaoRepository extends JpaRepository<CargosComissao, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT cc FROM CargosComissao cc " +
            "WHERE (cc.dataExercicio IS NULL AND cc.dataDemissaoExoneracao IS NULL) " +
            "OR " +
            "( " +
            "    ( " +
            "        cc.dataExercicio BETWEEN :dataInicio AND :dataFim " +
            "        OR " +
            "        cc.dataDemissaoExoneracao BETWEEN :dataInicio AND :dataFim " +
            "    ) " +
            "    OR " +
            "    ( " +
            "        cc.dataExercicio < :dataInicio " +
            "        AND " +
            "        (cc.dataDemissaoExoneracao > :dataFim OR cc.dataDemissaoExoneracao IS NULL) " +
            "   ) " +
            ") " )
    List<CargosComissao> buscarCargosComissaoPorPeriodo(@Param("dataInicio" ) Date dataInicio, @Param("dataFim" ) Date dataFim);

}
