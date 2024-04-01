package br.com.fullstack.moduloumsemanadez.controller;

import br.com.fullstack.moduloumsemanadez.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.fullstack.moduloumsemanadez.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno criarAluno(@RequestBody @Validated Aluno aluno) {
        return alunoService.criarAluno(aluno);
    }

    @GetMapping
    public List<Aluno> listarAluno(){
        return alunoService.listarAluno();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return alunoService.deletarAluno(id);
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long alunoId,
                                                    @RequestParam(value = "nome", required = false) String nome) {
        return alunoService.atualizarAluno(alunoId, nome);
    }
}
