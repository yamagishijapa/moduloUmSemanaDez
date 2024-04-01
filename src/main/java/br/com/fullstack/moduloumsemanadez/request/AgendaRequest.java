package br.com.fullstack.moduloumsemanadez.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendaRequest {

    @NotNull
    private Long alunoId;

    @NotNull
    private Long tutorId;

    @NotNull
    private LocalDate dataAgenda;

    @NotNull
    @Min(value = 1, message = "O status deve ser um dos listados: 1 - AGENDADO, 2 - CONFIRMADO, 3 - CANCELADO.")
    @Max(value = 3, message = "O status deve ser um dos listados: 1 - AGENDADO, 2 - CONFIRMADO, 3 - CANCELADO.")
    private Integer status;

    @NotNull
    private String tema;

    @NotNull
    private String descricao;
}
