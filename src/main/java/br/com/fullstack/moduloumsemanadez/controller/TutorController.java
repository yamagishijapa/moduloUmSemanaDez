package br.com.fullstack.moduloumsemanadez.controller;

import br.com.fullstack.moduloumsemanadez.model.Tutor;
import br.com.fullstack.moduloumsemanadez.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public Tutor criarTutor(@RequestBody @Validated Tutor tutor) {
        return tutorService.criarTutor(tutor);
    }

    @GetMapping
    public List<Tutor> listarTutor() {
        return tutorService.listarTutor();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return tutorService.deletarTutor(id);
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<?> atualizarTutor(@PathVariable Long tutorId,
                                            @RequestParam(value = "nome", required = false) String nome,
                                            @RequestParam(value = "especialidade", required = false) String especialidade) {
        return tutorService.atualizarTutor(tutorId, nome, especialidade);
    }
}
