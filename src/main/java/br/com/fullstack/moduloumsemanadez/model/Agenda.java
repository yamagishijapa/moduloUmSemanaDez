package br.com.fullstack.moduloumsemanadez.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
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
    private Date dataAgenda;

    @NotNull
    private String status;

    @NotNull
    private String tema;

    @NotNull
    private String description;
}
