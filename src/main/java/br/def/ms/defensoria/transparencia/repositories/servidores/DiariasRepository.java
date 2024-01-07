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

import br.def.ms.defensoria.transparencia.models.entity.Diarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DiariasRepository extends JpaRepository<Diarias, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT di FROM Diarias di " +
            "WHERE (di.dataHoraSaida BETWEEN :dataInicio AND :dataFim " +
            "        OR " +
            "       di.dataHoraChegada BETWEEN :dataInicio AND :dataFim " +
            ") ")
    List<Diarias> buscarDiariasPorPeriodo(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
