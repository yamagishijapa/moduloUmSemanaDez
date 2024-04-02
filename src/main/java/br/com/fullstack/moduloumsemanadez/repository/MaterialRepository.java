package br.com.fullstack.moduloumsemanadez.repository;

import br.com.fullstack.moduloumsemanadez.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Material m SET m.descricao = COALESCE(:descricao, m.descricao), m.caminhoArquivo = COALESCE(:caminhoArquivo, m.caminhoArquivo) WHERE m.id = :materialId")
    void updateMaterial(Long materialId, String descricao, String caminhoArquivo);
}
