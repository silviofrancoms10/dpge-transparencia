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

import br.def.ms.defensoria.transparencia.models.entity.CargosEfetivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CargosEfetivosRepository extends JpaRepository<CargosEfetivos, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT ce FROM CargosEfetivos ce " +
            "WHERE (ce.dataExercicio IS NULL AND ce.dataDemissaoExoneracao IS NULL) " +
            "OR " +
            "( " +
            "    ( " +
            "        ce.dataExercicio BETWEEN :dataInicio AND :dataFim " +
            "        OR " +
            "        ce.dataDemissaoExoneracao BETWEEN :dataInicio AND :dataFim " +
            "    ) " +
            "    OR " +
            "    ( " +
            "        ce.dataExercicio < :dataInicio " +
            "        AND " +
            "        (ce.dataDemissaoExoneracao > :dataFim OR ce.dataDemissaoExoneracao IS NULL) " +
            "   ) " +
            ") ")
    List<CargosEfetivos> buscarCargosEfetivosPorPeriodo(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
