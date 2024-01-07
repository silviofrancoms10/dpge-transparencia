    /*
     * Copyright (c) 2023 Defensoria Pública Geral de MS. All rights reserved.
     *
     * Defensoria Pública Geral de MS confidential and Proprietary information. It is strictly
     * forbidden for 3rd parties to modify, decompile, disassemble, defeat, disable
     * or circumvent any protection mechanism; to sell, license, lease, rent,
     * redistribute or make accessible to any third party, whether for profit or
     * without charge.
     */

    package br.def.ms.defensoria.transparencia.models.entity;

    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;

    import javax.persistence.Embeddable;
    import java.io.Serializable;

    @Getter
    @Setter
    @ToString
    @Embeddable
    public class SubsidioEfetivoId implements Serializable {
        private static final long serialVersionUID = 1L;

        private String carreira;
        private String nivel;
        private String classe;
    }
