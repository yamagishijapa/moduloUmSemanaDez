package br.com.fullstack.moduloumsemanadez.model;

import br.com.fullstack.moduloumsemanadez.enumeration.StatusAgenda;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Agenda {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agenda_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dataAgenda;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusAgenda status;

    @NotNull
    private String tema;

    @NotNull
    private String descricao;

    public Agenda(Aluno aluno, Tutor tutor, LocalDate dataAgenda, StatusAgenda status, String tema, String descricao) {
        this.aluno = aluno;
        this.tutor = tutor;
        this.dataAgenda = dataAgenda;
        this.status = status;
        this.tema = tema;
        this.descricao = descricao;
    }
}
