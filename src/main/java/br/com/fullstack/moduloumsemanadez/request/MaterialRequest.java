package br.com.fullstack.moduloumsemanadez.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialRequest {

    @NotNull
    private Long agendaId;

    @NotNull
    private String descricao;

    @NotNull
    private String caminhoArquivo;
}
