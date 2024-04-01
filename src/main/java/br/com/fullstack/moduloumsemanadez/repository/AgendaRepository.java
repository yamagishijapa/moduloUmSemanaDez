package br.com.fullstack.moduloumsemanadez.repository;

import br.com.fullstack.moduloumsemanadez.enumeration.StatusAgenda;
import br.com.fullstack.moduloumsemanadez.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Agenda a SET a.dataAgenda = COALESCE(:dataAgenda, a.dataAgenda), a.status = COALESCE(:status, a.status) WHERE a.id = :agendaId")
    void updateAgenda(Long agendaId, LocalDate dataAgenda, StatusAgenda status);

}