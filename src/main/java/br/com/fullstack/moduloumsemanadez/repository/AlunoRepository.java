package br.com.fullstack.moduloumsemanadez.repository;

import br.com.fullstack.moduloumsemanadez.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
