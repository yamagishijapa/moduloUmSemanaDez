package br.com.fullstack.moduloumsemanadez.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
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
    private String descricao;

    @NotNull
    private String caminhoArquivo;

    public Material(Agenda agenda, String descricao, String caminhoArquivo) {
        this.agenda = agenda;
        this.descricao = descricao;
        this.caminhoArquivo = caminhoArquivo;
    }
}
