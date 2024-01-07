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

import br.def.ms.defensoria.transparencia.models.entity.ListaEmail;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServidoresRepository extends JpaRepository<ListaEmail, Long> {

    @Transactional(readOnly = true)
    @Query("select email from ListaEmail email where email.tipoServidor = :tipoServidor order by email.nome asc")
    List<ListaEmail> findByTipoServidor(@Param("tipoServidor") EnumTipoServidor tipoServidor);
}
