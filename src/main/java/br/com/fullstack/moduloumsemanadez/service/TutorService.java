package br.com.fullstack.moduloumsemanadez.service;

import br.com.fullstack.moduloumsemanadez.model.Tutor;
import br.com.fullstack.moduloumsemanadez.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Tutor criarTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public List<Tutor> listarTutor(){
        return tutorRepository.findAll();
    }

    public ResponseEntity<?> deletarTutor(Long id) {
        if(!tutorRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O tutor com ID " + id + " não foi encontrado.");
        }

        tutorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> atualizarTutor(Long tutorId, String nome, String especialidade) {
        if (!tutorRepository.existsById(tutorId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutor com ID " + tutorId + " não encontrado.");
        }

        tutorRepository.updateTutor(tutorId, nome, especialidade);

        return ResponseEntity.ok(tutorRepository.findById(tutorId));
    }
}
