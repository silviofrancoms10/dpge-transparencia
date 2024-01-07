/*
 * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
 *
 * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
 * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
 * or circumvent any protection mechanism; to sell, license, lease, rent,
 * redistribute or make accessible to any third party, whether for profit or
 * without charge.
 */

package br.def.ms.defensoria.sboot.autoconfigure.transparencia;

import br.def.ms.defensoria.transparencia.repositories.convenios.ContratosConveniosRepository;
import br.def.ms.defensoria.transparencia.repositories.servidores.*;
import br.def.ms.defensoria.transparencia.services.*;
import br.def.ms.defensoria.transparencia.services.integration.PncpAuthProperties;
import br.def.ms.defensoria.transparencia.services.integration.PncpIntegrationService;
import br.def.ms.defensoria.transparencia.services.integration.SpfAuthProperties;
import br.def.ms.defensoria.transparencia.services.integration.SpfIntegrationService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ServidoresService servidoresService(ServidoresRepository servidoresRepository, ModelMapper modelMapper) {
        return new ServidoresService(servidoresRepository, modelMapper);
    }

    @Bean
    public QuadroDefensoriaPublicaService quadroDefensoriaPublicaService(QuadroDefensoriaPublicaRepository quadroDefensoriaPublicaRepository, ModelMapper modelMapper) {
        return new QuadroDefensoriaPublicaService(quadroDefensoriaPublicaRepository, modelMapper);
    }

    @Bean
    public CargosEfetivosService cargosEfetivosService(CargosEfetivosRepository cargosEfetivosRepository, ModelMapper modelMapper) {
        return new CargosEfetivosService(cargosEfetivosRepository, modelMapper);
    }

    @Bean
    public CargosComissaoService cargosComissaoService(CargosComissaoRepository cargosComissaoRepository, ModelMapper modelMapper) {
        return new CargosComissaoService(cargosComissaoRepository, modelMapper);
    }

    @Bean
    public SubsidioDefensorService subsidioDefensorService(SubsidioDefensorRepository subsidioDefensorRepository, ModelMapper modelMapper) {
        return new SubsidioDefensorService(subsidioDefensorRepository, modelMapper);
    }

    @Bean
    public SubsidioEfetivoService subsidioEfetivoService(SubsidioEfetivoRepository subsidioEfetivoRepository, ModelMapper modelMapper) {
        return new SubsidioEfetivoService(subsidioEfetivoRepository, modelMapper);
    }

    @Bean
    public SubsidioCargosComissaoService subsidioCargosComissaoService(SubsidioCargosComissaoRepository subsidioCargosComissaoRepository, ModelMapper modelMapper) {
        return new SubsidioCargosComissaoService(subsidioCargosComissaoRepository, modelMapper);
    }

    @Bean
    public ConsultaDeRemuneracaoService consultaDeRemuneracaoService(ConsultaDeRemuneracaoRepository consultaDeRemuneracaoRepository, ModelMapper modelMapper) {
        return new ConsultaDeRemuneracaoService(consultaDeRemuneracaoRepository, modelMapper);
    }

    @Bean
    public DiariasService diariasService(DiariasRepository diariasRepository, ModelMapper modelMapper) {
        return new DiariasService(diariasRepository, modelMapper);
    }

    @Bean
    public ContratosConveniosService contratosConveniosService(ContratosConveniosRepository contratosConveniosRepository, ModelMapper modelMapper) {
        return new ContratosConveniosService(contratosConveniosRepository, modelMapper);
    }

    @Bean
    public PncpIntegrationService pncpIntegrationService(PncpAuthProperties pncpAuthProperties) {
        return new PncpIntegrationService(pncpAuthProperties);
    }

    @Bean
    public SpfIntegrationService spfIntegrationService(SpfAuthProperties spfAuthProperties) {
        return new SpfIntegrationService(spfAuthProperties);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
