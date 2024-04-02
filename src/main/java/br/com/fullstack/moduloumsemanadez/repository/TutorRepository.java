package br.com.fullstack.moduloumsemanadez.repository;

import br.com.fullstack.moduloumsemanadez.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Tutor t SET t.nome = " +
            "COALESCE(:nome, t.nome), t.especialidade = " +
            "COALESCE(:especialidade, t.especialidade) WHERE t.id = :tutorId")
    void updateTutor(Long tutorId, String nome, String especialidade);

}
