/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.transparencia.controller;

import br.def.ms.defensoria.transparencia.controllers.ServidoresController;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailRequest;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResource;
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResponse;
import br.def.ms.defensoria.transparencia.models.enums.EnumTipoServidor;
import br.def.ms.defensoria.transparencia.services.ServidoresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ServidoresControllerTest {
    private ServidoresService servidoresService;

    private ServidoresController classUnderTest;

    @BeforeEach
    void initMocks() {
        servidoresService = mock(ServidoresService.class);
        classUnderTest = new ServidoresController(servidoresService);
    }

    @Test
    void testListaDeEmails() {
        /* Preparation */
        ListaEmailRequest request = new ListaEmailRequest();
        ListaEmailResource listaEmailResource = new ListaEmailResource();
        listaEmailResource.setTipoServidor(EnumTipoServidor.SERVIDOR_COMISSIONADO);
        request.setListaEmailResource(listaEmailResource);

        /* Stubbing */
        when(servidoresService.obterListaEmailPorTipoServidor(request))
                .thenReturn(new ListaEmailResponse());

        /* Execution */
        ListaEmailResponse result = classUnderTest.retornaListaDeEmailsPorTipoServidor(request);

        /* Verification */
        verify(servidoresService).obterListaEmailPorTipoServidor(request);
        assertNotNull(result);
    }
}
