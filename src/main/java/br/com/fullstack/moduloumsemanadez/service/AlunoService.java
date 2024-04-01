package br.com.fullstack.moduloumsemanadez.service;

import br.com.fullstack.moduloumsemanadez.model.Aluno;
import br.com.fullstack.moduloumsemanadez.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAluno(){
        return alunoRepository.findAll();
    }

    public ResponseEntity<?> deletarAluno(Long id) {
        if(!alunoRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O aluno com ID " + id + " não foi encontrado.");
        }

        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> atualizarAluno(Long alunoId, String nome) {
        if (!alunoRepository.existsById(alunoId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com ID " + alunoId + " não encontrado.");
        }

        Optional<Aluno> aluno = alunoRepository.findById(alunoId);
        aluno.get().setNome(nome);

        alunoRepository.save(aluno.get());

        return ResponseEntity.ok(alunoRepository.findById(alunoId));
    }
}
