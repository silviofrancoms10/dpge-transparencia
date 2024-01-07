/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.services;

import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.EmailData;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResponse;
import br.def.ms.defensoria.transparencia.models.entity.ListaEmail;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import br.def.ms.defensoria.transparencia.repositories.servidores.ServidoresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServidoresServiceTest {
    private ServidoresService classUnderTest;
    private ServidoresRepository servidoresRepository;

    @BeforeEach
    void initMocks() {
        servidoresRepository = mock(ServidoresRepository.class);
        classUnderTest = new ServidoresService(servidoresRepository, new ModelMapper());
    }

    @Test
    void obterListaEmailPorTipoServidorPorTipo() {
        /* Preparation */
        ListaEmailRequest listaEmailRequest = new ListaEmailRequest();
        ListaEmailResource listaEmailResource = new ListaEmailResource();
        listaEmailResource.setTipoServidor(EnumTipoServidor.DEFENSOR);
        listaEmailRequest.setListaEmailResource(listaEmailResource);

        List<ListaEmail> listaEmails = new ArrayList<>();
        ListaEmail listaEmail1 = new ListaEmail();
        listaEmail1.setNome("Rafael Silva");
        listaEmail1.setEmailFuncional("rafael.silva@example.com");
        listaEmail1.setEmailPessoal("rafael.silva@gmail.com");
        listaEmail1.setTipoServidor(EnumTipoServidor.DEFENSOR);
        listaEmails.add(listaEmail1);

        /* Stubbing */
        when(servidoresRepository.findByTipoServidor(EnumTipoServidor.DEFENSOR)).thenReturn(listaEmails);

        /* Execution */
        ListaEmailResponse listaEmailResponse = classUnderTest.obterListaEmailPorTipoServidor(listaEmailRequest);

        /* Verification */
        assertNotNull(listaEmailResponse);
        assertEquals(1, listaEmailResponse.getListaEmailData().size());

        EmailData emailData = listaEmailResponse.getListaEmailData().get(0);
        assertEquals("Rafael Silva", emailData.getNome());
        assertEquals("rafael.silva@example.com", emailData.getEmailFuncional());
        assertEquals("rafael.silva@gmail.com", emailData.getEmailPessoal());
        assertEquals(EnumTipoServidor.DEFENSOR, emailData.getTipoServidor());
    }
}
