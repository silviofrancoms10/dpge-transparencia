/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.repositories.convenios;

import br.def.ms.defensoria.transparencia.models.entity.ContratosConvenios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ContratosConveniosRepository extends JpaRepository<ContratosConvenios, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT cc FROM ContratosConvenios cc " +
            "WHERE (cc.vigenciaInicial BETWEEN :dataInicio AND :dataFim  " +
            "OR cc.vigenciaFinal BETWEEN :dataInicio AND :dataFim)")
    List<ContratosConvenios> buscarContratosConveniosPorAno(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

    @Transactional(readOnly = true)
    @Query(value = "SELECT cc FROM ContratosConvenios cc " +
            "WHERE cc.identificacaoUnica LIKE %:numeroProcesso% " +
            "AND cc.nomeContratada LIKE %:nomeContratada% " +
            "AND cc.modalidade LIKE %:modalidade% " +
            "AND cc.encerrado = :encerrado "
    )
    List<ContratosConvenios> buscarContratosConveniosPorParametros(
            @Param("numeroProcesso") String identificacaoUnica,
            @Param("nomeContratada") String nomeContratada,
            @Param("modalidade") String modalidade,
            @Param("encerrado") Integer encerrado
    );
}