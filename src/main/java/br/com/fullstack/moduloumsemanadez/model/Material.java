package br.com.fullstack.moduloumsemanadez.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Material {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long material_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @NotNull
    private String description;

    @NotNull
    private String caminhoArquivo;
}
