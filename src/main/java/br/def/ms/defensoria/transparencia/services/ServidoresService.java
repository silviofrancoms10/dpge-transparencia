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
import br.def.ms.defensoria.transparencia.models.dto.servidores.emails.ListaEmailResponse;
import br.def.ms.defensoria.transparencia.models.entity.ListaEmail;
import br.def.ms.defensoria.transparencia.repositories.servidores.ServidoresRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class ServidoresService {
    private final ServidoresRepository servidoresRepository;
    private final ModelMapper modelMapper;

    public ServidoresService(ServidoresRepository servidoresRepository, ModelMapper modelMapper) {
        this.servidoresRepository = servidoresRepository;
        this.modelMapper = modelMapper;
    }

    public ListaEmailResponse obterListaEmailPorTipoServidor(@Valid @RequestBody ListaEmailRequest listaEmailRequest) {
        ListaEmailResponse listaEmailResponse = new ListaEmailResponse();
        List<ListaEmail> listaEmails = servidoresRepository.findByTipoServidor(listaEmailRequest.getListaEmailResource().getTipoServidor());

        List<EmailData> data = listaEmails.stream()
                .map(email -> modelMapper.map(email, EmailData.class))
                .collect(Collectors.toList());

        listaEmailResponse.setListaEmailData(data);

        return listaEmailResponse;
    }
}
