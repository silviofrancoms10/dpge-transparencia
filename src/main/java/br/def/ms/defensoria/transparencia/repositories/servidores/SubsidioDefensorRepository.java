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

import br.def.ms.defensoria.transparencia.models.entity.SubsidioDefensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubsidioDefensorRepository extends JpaRepository<SubsidioDefensor, Long> {

    @Transactional(readOnly = true)
    @Query(value = " SELECT * " +
            " FROM BD_DEFENSORIA_GESTAOPESSOAL.dbo.VIEW_TABELA_SALARIAL_DEFENSOR tabela " +
            " WHERE DATEPART(YEAR, tabela.dataCompetencia) = :ano" +
            " ORDER BY tabela.valorReajuste DESC", nativeQuery = true)
    List<SubsidioDefensor> buscarSubsidioDefensorPorAno(Integer ano);

    @Transactional(readOnly = true)
    @Query(value = " SELECT DATEPART(YEAR, tabela.dataCompetencia) " +
            " FROM BD_DEFENSORIA_GESTAOPESSOAL.dbo.VIEW_TABELA_SALARIAL_DEFENSOR tabela " +
            " GROUP BY DATEPART(YEAR, tabela.dataCompetencia)", nativeQuery = true)
    List<Integer> buscarListaAno();
}
